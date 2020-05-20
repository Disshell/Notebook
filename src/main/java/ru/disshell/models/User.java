package ru.disshell.models;

public class User {
    private int id;
    private  String login;

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

    public User(int id, String login) {
        this.id = id;
        this.login = login;
    }

    public User(){

    }
}
