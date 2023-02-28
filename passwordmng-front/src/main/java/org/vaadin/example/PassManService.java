package org.vaadin.example;

import java.io.Serializable;
import java.sql.*;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class PassManService implements Serializable {

    private static final String URL = "jdbc:mysql://localhost:6603/PasswordManager";
    private static final String USER = "root";
    private static final String PASSWORD = "passwd";

    public int loginUsername(String mail, String password) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.println("Conexión exitosa");

            String sql = "SELECT * FROM usuarios WHERE mail=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, mail);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs);
            // Miramos los resultados de la query
            if (rs.next()) {
                return 0;
            } else {
                // Si el usuario no existe, mandamos un false
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
