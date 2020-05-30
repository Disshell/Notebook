package ru.disshell.controllers;

import ru.disshell.models.Note;
import ru.disshell.models.User;
import ru.disshell.repositories.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class HomeServlet extends javax.servlet.http.HttpServlet {

    Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        storage = Storage.getInstance();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String date = request.getParameter("date").toString();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        format.applyPattern("dd.MM.yyyy");
        Date dt = new Date();
        try {
            dt = format.parse(date);
        } catch (ParseException e) {

        }
        String text = request.getParameter("text").toString();
        Note note = new Note();
        User user =storage.users.findByLogin(request.getSession().getAttribute("login").toString());
        note.setText(text);
        note.setDate(dt);
        note.setIsChecked(false);
        note.setUser(user);
        storage.notes.add(note);
        response.sendRedirect(request.getContextPath() + "/");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        if(request.getParameter("delId") != null){
            int id = Integer.parseInt(request.getParameter("delId"));
            storage.notes.delete(id);
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        if(request.getParameter("chId") != null){
            int id = Integer.parseInt(request.getParameter("chId"));
            Note note = storage.notes.get(id);
            note.setIsChecked(!note.getIsChecked());
            storage.notes.edit(note);
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String login = (String) request.getSession().getAttribute("login");
        User user = storage.users.findByLogin(login);
        Set<Note> notes = user.getNotes();
        request.setAttribute("notes", notes);
        request.getRequestDispatcher("/views/notes.jsp").forward(request, response);
    }
}
