package Repository.BookRepository;

import Model.Book;

import javax.persistence.*;
import java.util.List;

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
        String strQuery = "SELECT c FROM Book c WHERE c.id IS NOT NULL";
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
}
