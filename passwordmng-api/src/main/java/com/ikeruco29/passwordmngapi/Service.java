package com.ikeruco29.passwordmngapi;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class Service {
    private static final String URL = "jdbc:mysql://localhost:6603/PasswordManager";
    private static final String USER = "root";
    private static final String PASSWORD = "passwd";

    public String loginUsername(@RequestParam("user") String usuario, @RequestParam("pwd") String password) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.println("Conexión exitosa");

            String sql = "SELECT * FROM usuarios WHERE mail=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            // Miramos los resultados de la query
            if (rs.next()) {

            } else {
                // Si el usuario no existe, mandamos un false
                return false;
            }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
