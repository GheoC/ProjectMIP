package Repository.UserRepository;


import Model.User;

import javax.persistence.*;

public class UserRepository
{

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("connetionDB");


    public void addUserToDatabase(User user)
    {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = null;

        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            entityManager.persist(user);
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


    public User findUser(String email)
    {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT c from User c WHERE c.email = :email";

        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
        typedQuery.setParameter("email", email);
        User user = null;

            user = typedQuery.getResultList().stream().findFirst().orElse(null);
            entityManager.close();

            if (user==null) return null;

            return user;
    }
}
