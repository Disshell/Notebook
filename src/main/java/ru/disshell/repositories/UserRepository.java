package ru.disshell.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.disshell.models.User;

import java.util.Collection;
import java.util.List;

public class UserRepository{

    private final SessionFactory factory;
    public UserRepository(){
        factory = new Configuration().configure().buildSessionFactory();
    }

    public Collection<User> Users() {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.createQuery("from User ").list();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public int add(User user) {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(user);
            return user.getId();
        }finally {
            transaction.commit();
            session.close();
        }
    }

    public void edit(User user) {

    }

    public void delete(int id) {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(new User(id, null, null));
        } finally {
            transaction.commit();
        }

    }

    public User get(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try{
            User user = session.get(User.class, id);
            user.getNotes();
            return user;
        }finally {
            tx.commit();
            session.close();
        }
    }

    public User findByLogin(String Login) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            final Query query = session.createQuery("from User as user where user.login=:login").setParameter("login", Login);
            User user = (User) query.iterate().next();
            user.getNotes();
            return user;
        } finally {
            transaction.commit();
            session.close();
        }
    }

    public User findAuth(String login, String password) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            final Query query = session.createQuery("from User as user where user.login =:login and user.password=:password")
                    .setParameter("login", login)
                    .setParameter("password", password);
            List<User> users = query.list();
            return users.isEmpty() ? null : users.iterator().next();
        } finally {
            transaction.commit();
            session.close();
        }
    }

}
