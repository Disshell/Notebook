package ru.disshell;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.disshell.models.User;

import java.util.Collection;

public class HibernateStorage implements IStorage {

    private final SessionFactory factory;
    public HibernateStorage(){
        factory = new Configuration().configure().buildSessionFactory();
    }


    @Override
    public Collection<User> users() {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.createQuery("from User ").list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public int Add(User user) {
        return 0;
    }

    @Override
    public void edit(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User get(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try{
            User user = session.get(User.class, id);
            return user;
        }finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public User findByLogin(String Login) {
        return null;
    }

    @Override
    public void close() {

    }
}
