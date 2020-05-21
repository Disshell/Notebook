package ru.disshell.models.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.disshell.models.User;

import java.util.Collection;
import java.util.List;

public class UserRepository implements IUserRepository {

    private final SessionFactory factory;
    public UserRepository(){
        factory = new Configuration().configure().buildSessionFactory();
    }


    @Override
    public Collection<User> users() {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.createQuery("from User ").list();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public int Add(User user) {
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

    @Override
    public void edit(User user) {

    }

    @Override
    public void delete(int id) {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(new User(id, null, null));
        } finally {
            transaction.commit();
        }

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
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            final Query query = session.createQuery("from User as user where user.login=:login").setParameter("login", Login);
            return (User) query.iterate().next();
        } finally {
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void close() {
        this.factory.close();
    }

    @Override
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
