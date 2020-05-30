package ru.disshell.controllers;

import ru.disshell.models.User;
import ru.disshell.repositories.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    public Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        storage = Storage.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        storage.users.add(user);
        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("password", password);
        response.sendRedirect(request.getContextPath()+ "/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/registration.jsp").forward(request, response);
    }

}
