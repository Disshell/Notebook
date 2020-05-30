package ru.disshell.repositories;

public class Storage {
    private static final  Storage INSTANCE = new Storage();
    public NoteRepository notes;
    public UserRepository users;
    private Storage(){
        notes = new NoteRepository();
        users = new UserRepository();
    }

    public static Storage getInstance() {
        return INSTANCE;
    }
}
