package ru.disshell.models;

import java.util.List;
import java.util.Set;

public class User {
    private int id;
    private  String login;
    private String password;
    private Set<Note> notes;

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
    public User(){

    }

    public Set<Note> getNotes() {
        return notes;
    }
    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public int getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}
