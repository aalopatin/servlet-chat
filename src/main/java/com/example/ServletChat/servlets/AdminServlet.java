package com.example.ServletChat.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter() ) {
            writer.write("<html><body>");
            writer.write("<form action=\"admin\" method=\"POST\">");
            writer.write("Block: <select name=\"block\"><option>Access</option><option>Message</option></select>");
            writer.write("<br/><br/>");
            writer.write("User: <input name=\"user\" />");
            writer.write("<input type=\"submit\" value=\"Submit\" />");
            writer.write("</form>");
            writer.write("</body></html>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String user = request.getParameter("user");
        String block = request.getParameter("block");
        Set<String> listOfUsers;
        listOfUsers = (Set<String>) servletContext.getAttribute("block" + block);
        if(listOfUsers == null) {
            listOfUsers = new HashSet<>();
        }
        listOfUsers.add(user);
        servletContext.setAttribute("block" + block, listOfUsers);
        response.sendRedirect(request.getContextPath() + request.getServletPath());
    }
}
