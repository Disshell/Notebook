package ru.disshell.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.disshell.models.Note;

import java.util.Collection;
import java.util.List;

public class NoteRepository {
    private final SessionFactory factory;

    public NoteRepository(){
        factory = new Configuration().configure().buildSessionFactory();
    }


    public Collection<Note> Notes() {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.createQuery("from Note ").list();
        } finally {
            transaction.commit();
            session.close();
        }
    }


    public int add(Note note) {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(note);
            return note.getId();
        }finally {
            transaction.commit();
            session.close();
        }
    }


    public void edit(Note Note) {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(Note);
        } finally {
            transaction.commit();
            session.close();
        }

    }


    public void delete(int id) {
        final Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Note note = new Note();
            note.setId(id);
            session.delete(note);
        } finally {
            transaction.commit();
        }

    }


    public Note get(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try{
            Note note = session.get(Note.class, id);
            return note;
        }finally {
            tx.commit();
            session.close();
        }
    }


    public List<Note> findByUser(int id) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            final Query query = session.createQuery("from Note as note where note.id=:id").setParameter("id",id);
            return query.list();
        } finally {
            transaction.commit();
            session.close();
        }
    }

}
