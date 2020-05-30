package ru.disshell.filters;

import ru.disshell.repositories.Storage;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    public Storage storage;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        storage = Storage.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        final HttpSession session = request.getSession();

        if(session.getAttribute("login") != null  && session.getAttribute("password") != null){
            filterChain.doFilter(request, response);
        } else if(storage.users.findAuth(login, password) != null){
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("password", password);
            filterChain.doFilter(request, response);
        }
        else {
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
