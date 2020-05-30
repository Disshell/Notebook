package ru.disshell.controllers;

import ru.disshell.models.Note;
import ru.disshell.models.User;
import ru.disshell.repositories.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class HomeServlet extends javax.servlet.http.HttpServlet {

    Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        storage = Storage.getInstance();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String login = (String) request.getSession().getAttribute("login");
        User user = storage.users.findByLogin(login);
        Set<Note> notes = user.getNotes();
        request.setAttribute("notes", notes);
        request.getRequestDispatcher("/views/notes.jsp").forward(request, response);
    }
}
