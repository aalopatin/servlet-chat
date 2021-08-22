package com.example.ServletChat.servlets;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

public class AccessFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        ServletContext servletContext = getServletContext();
        HttpSession session = request.getSession();

        String user = (String) session.getAttribute("name");

        Set<String> listOfUsers = (Set<String>) servletContext.getAttribute("blockAccess");

        if (listOfUsers != null) {
            if (listOfUsers.contains(user)) {
                response.getWriter().write("Access is denied!");
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
