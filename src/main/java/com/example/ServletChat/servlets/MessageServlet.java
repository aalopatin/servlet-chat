package com.example.ServletChat.servlets;

import com.example.ServletChat.model.Message;
import com.example.ServletChat.service.MessageRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        String textMsg = request.getParameter("message");
        Timestamp datetime = Timestamp.valueOf(LocalDateTime.now());

        MessageRepository.saveMassage(new Message(datetime, name, textMsg));

        response.sendRedirect("/");

    }
}
