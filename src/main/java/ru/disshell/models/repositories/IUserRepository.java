package ru.disshell.models.repositories;

import ru.disshell.models.User;

import java.util.Collection;

public interface IUserRepository {
    public Collection<User> users();
    public int Add(final User user);
    public void edit (final User user);
    public void delete(final int id);
    public User get(final int id);
    public User findByLogin(final String Login);
    public void close();
    public User findAuth(String login, String password);
}
