package ru.disshell;

import org.junit.Test;
import ru.disshell.models.User;

import java.util.List;

import static org.junit.Assert.*;

public class HibernateStorageTest {

    @Test
    public void users() {
        assertEquals(1,1);
    }

    @Test
    public void add() {
        assertEquals(1,1);
    }

    @Test
    public void edit() {
        assertEquals(1,1);
    }

    @Test
    public void delete() {
        assertEquals(1,1);
    }

    @Test
    public void get() {
        final HibernateStorage storage = new HibernateStorage();
        int id = 1;
        User user = storage.get(1);
        assertEquals(1 ,user.getId());
    }

    @Test
    public void findByLogin() {
        assertEquals(1,1);
    }

    @Test
    public void close() {
        assertEquals(1,1);
    }
}