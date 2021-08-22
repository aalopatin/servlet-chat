package com.example.ServletChat.servlets;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.example.ServletChat.model.Message;
import com.example.ServletChat.service.MessageRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();

        String name = (String) request.getSession().getAttribute("name");

        String path;

        if (name != null) {
            List<Message> messages = MessageRepository.getMessages();
            request.setAttribute("messages", messages);
            path = "/chat.jsp";
        } else {
            path = "/login.jsp";
        }

//        response.sendRedirect(path);

        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }



}