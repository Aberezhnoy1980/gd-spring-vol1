package ru.aberezhnoy;

import org.hibernate.cfg.Configuration;
import ru.aberezhnoy.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        // INSERT

//        em.getTransaction().begin();
//        em.persist(new User(null, "Stepan", "passStepan"));
//        em.persist(new User(null, "Bob", "passBob"));
//        em.persist(new User(null, "Kakoitamuzhik", "passKakoitamuzhik"));
//        em.getTransaction().commit();

//       // SELECT

//        System.out.println("User:");
//        User user = em.find(User.class, 1L);
//        System.out.println(user);
//
//        System.out.println("Users:");
//        List<User> users = em.createQuery("select u from User u where u.username = :username", User.class)
//                .setParameter("username", "Stepan")
//                .getResultList();
//        System.out.println(users);
//
//        User user1 = em.createNativeQuery("select * from users where username = kakkoitamuzhik", User.class)
//                .getSingleResult();
//        System.out.println(user1);

        // UPDATE

//        User user = em.find(User.class, 1L);
//        em.getTransaction().begin();
//        user.setPassword("passAlex");
//        em.getTransaction().commit();

//        User user = new User(1L, "Petr", "passPetr");
//        em.getTransaction().begin();
//        em.merge(user);
//        em.getTransaction().commit();

//        em.getTransaction().begin();
//        em.createQuery("update User set username = :username, password = :password where id = :id")
//                .setParameter("username", "Kakoitamuzhik")
//                .setParameter("password", "passKakoitamuzhik")
//                .setParameter("id",6L)
//                        .executeUpdate();
//        em.getTransaction().commit();

        // DELETE

//        em.getTransaction().begin();
//        User user = em.find(User.class, 1L);
//        em.remove(user);
//        em.getTransaction().commit();

//        em.getTransaction().begin();
//        em.createQuery("delete from User where id = :id")
//                .setParameter("id", 1L)
//                .executeUpdate();
//        em.getTransaction().commit();

        em.close();
    }
}
