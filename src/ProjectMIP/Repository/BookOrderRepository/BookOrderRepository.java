package Repository.BookOrderRepository;

import Model.Book;
import Model.BookOrder;
import Model.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class BookOrderRepository
{
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("connetionDB");


    public void addBookOrders(User user, Set<Book> books, Integer period)
    {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            for (Book book:books)
            {
                BookOrder bookOrder = new BookOrder();
                bookOrder.setBook(book);
                bookOrder.setUser(user);
                bookOrder.setLendingTime(period);
                bookOrder.setActive(true);
                bookOrder.setLendingDate(LocalDate.now());
                bookOrder.setDeadline(LocalDate.now().plusDays(period));
                bookOrder.setReceivedDate(null);
                entityManager.persist(bookOrder);
            }

            entityTransaction.commit();

        } catch (Exception exception) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
    }


    public List<BookOrder> findBookOrderByUserId(Integer userId)
    {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT c from BookOrder c WHERE c.user.id = :userId";
        TypedQuery<BookOrder> typedQuery = entityManager.createQuery(query, BookOrder.class);
        typedQuery.setParameter("userId", userId);
        List<BookOrder> bookOrders = null;

        bookOrders = typedQuery.getResultList();

        System.out.println(bookOrders);
        entityManager.close();

        if (bookOrders==null) return null;

        return bookOrders;
    }
}
