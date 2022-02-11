package Repository.BookRepository;

import Model.Book;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class BookRepository
{
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("connetionDB");

    public void addBookToDatabase(Book book)
    {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            entityManager.persist(book);
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

    public List<Book> getBooks()
    {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT c FROM Book c WHERE c.id IS NOT NULL AND c.itemsAvailable > 0";
        TypedQuery<Book> typedQuery = entityManager .createQuery(strQuery, Book.class);
        List<Book> books;

        try
        {
            books = typedQuery.getResultList();
            return books;

        }catch (NoResultException exception) {
            exception.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    public Book findBookById(Integer id)
    {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT c from Book c WHERE c.id = :id";

        TypedQuery<Book> typedQuery = entityManager.createQuery(query, Book.class);
        typedQuery.setParameter("id", id);
        Book book = null;

        book = typedQuery.getResultList().stream().findFirst().orElse(null);
        entityManager.close();

        if (book==null) return null;

        return book;
    }


    public void decreaseItemsForBooks(Set<Book> books)
    {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT c FROM Book c WHERE c.id IS NOT NULL";
        TypedQuery<Book> typedQuery = entityManager .createQuery(strQuery, Book.class);

        for (Book book:books)
        {
            Book tempBook = entityManager.find(Book.class,book.getId());
            entityManager.getTransaction().begin();
            tempBook.setItemsAvailable(tempBook.getItemsAvailable()-1);
            entityManager.getTransaction().commit();
        }
            entityManager.close();
    }

    public void increaseItemsForBooks(List<Integer> bookIds)
    {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
//        String strQuery = "SELECT c FROM Book c WHERE c.id IS NOT NULL";
//        TypedQuery<Book> typedQuery = entityManager .createQuery(strQuery, Book.class);

        for (int i = 0; i <bookIds.size(); i++) {
            Book tempBook = entityManager.find(Book.class, bookIds.get(i));
            entityManager.getTransaction().begin();
            tempBook.setItemsAvailable(tempBook.getItemsAvailable()+1);
            entityManager.getTransaction().commit();
        }
        entityManager.close();
    }
}
