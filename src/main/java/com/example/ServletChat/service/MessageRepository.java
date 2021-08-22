package com.example.ServletChat.service;

import com.example.ServletChat.model.Message;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MessageRepository {

    private static String selectQuery = "SELECT datetime, usr, message  FROM public.message;";

    private static String insertQuery = "INSERT INTO public.message(usr, message, datetime) VALUES (?, ?, ?)";

    public static List<Message> getMessages() {

        List<Message> messages = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stat = conn.prepareStatement(selectQuery)) {

            try (ResultSet rs = stat.executeQuery()) {
                while (rs.next()) {
                    Message message = new Message(
                            rs.getTimestamp(1),
                            rs.getString(2),
                            rs.getString(3)
                    );
                    messages.add(message);
                }
            }

        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }

        return messages;

    }

    public static void saveMassage(Message message) {

        try (Connection conn = getConnection();
             PreparedStatement stat = conn.prepareStatement(insertQuery)) {

            stat.setString(1, message.getUser());
            stat.setString(2, message.getMessage());
            stat.setTimestamp(3, message.getDatetime());

            int row = stat.executeUpdate();

        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }

    }

    private static Connection getConnection() throws SQLException, IOException {
        var props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("application.properties"))) {
            props.load(in);
        }
        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) {
            System.setProperty("jdbc.drivers", drivers);
        }
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        return DriverManager.getConnection(url, username, password);
    }

}
