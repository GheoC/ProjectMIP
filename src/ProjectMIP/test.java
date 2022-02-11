import Model.BookOrder;
import Service.BookOrderService.BookOrderService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class test
{
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("connetionDB");

    public static void main(String[] args)
    {

//        UserService userService = new UserService();
//
//        User user = userService.findUserByEmail("marcu2@gmail.com");
//
//        if (user ==null) {
//            System.out.println("User not found");
//        } else
//        {
//            System.out.println(user.toString());
//        }

//        String encryptedPassword = DigestUtils.md5Hex("admin").toUpperCase();
//        System.out.println(encryptedPassword);
//
//        User user = new UserRepository().findUser("carla@gmail.com");
//        System.out.println(user);
//
//        Book book1 = new BookRepository().getBooks().get(2);
//        Book book2 = new BookRepository().getBooks().get(5);
//
//        System.out.println(book1);
//        System.out.println(book2);
//
//        BookOrder bookOrder = new BookOrder();
//        bookOrder.setUser(user);
//        bookOrder.setBook(book1);
//        bookOrder.setLendingTime(3);
//        bookOrder.setLendingDate(LocalDate.now());
//
//        Set<Book> bookSet = new HashSet<>();
//        bookSet.add(book1);
//        bookSet.add(book2);
//
//
//        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
//        EntityTransaction entityTransaction = null;
//        try {
//            entityTransaction = entityManager.getTransaction();
//            entityTransaction.begin();
//
//        for (Book book:bookSet)
//        {
//            BookOrder bookOrder1 = new BookOrder();
//            bookOrder1.setBook(book);
//            bookOrder1.setUser(user);
//            bookOrder1.setLendingTime(3);
//            bookOrder1.setLendingDate(LocalDate.now());
////            bookOrder.setDealine(LocalDate.now().plusDays(3));
////            bookOrder.setReceivedDate(null);
//            entityManager.persist(bookOrder1);
//        }
//            entityTransaction.commit();
//
//        } catch (Exception exception) {
//            if (entityTransaction != null) {
//                entityTransaction.rollback();
//            }
//            exception.printStackTrace();
//        } finally {
//            entityManager.close();
//        }
//

        List<BookOrder> bookOrder = new BookOrderService().findBookOrder(20);

        System.out.println(bookOrder);

    }
}
