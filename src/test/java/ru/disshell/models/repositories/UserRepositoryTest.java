package ru.disshell.models.repositories;

import org.junit.Test;
import ru.disshell.models.User;
import ru.disshell.repositories.UserRepository;

import static org.junit.Assert.*;

public class UserRepositoryTest {

    @Test
    public void TestUsers(){
        final UserRepository repository = new UserRepository();
        final int id = repository.add(new User(-1, "TestLogin", "123"));
        User user = repository.get(id);
        assertEquals(user.getId(), repository.findAuth(user.getLogin(), user.getPassword()).getId());
        assertEquals(id ,user.getId());
        assertEquals(id , repository.findByLogin("TestLogin").getId());
        repository.delete(id);
        assertNull(repository.get(id));
    }
}