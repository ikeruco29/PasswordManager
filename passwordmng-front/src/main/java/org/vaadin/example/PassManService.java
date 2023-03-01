package org.vaadin.example;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PassManService implements Serializable {

    private static final String URL = "jdbc:mysql://localhost:6603/PasswordManager";
    private static final String USER = "root";
    private static final String PASSWORD = "passwd";

    public boolean authenticate(String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        // Authenticate the user based on the username and password
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuarios WHERE mail = ? AND password = ?");
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    public User getUser(String username) throws SQLException {
        // Preparamos la query
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuarios WHERE mail = ?");
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        // Si la query es correcta...
        if (resultSet.next()) {
            int userIndex = resultSet.getInt("userId");
            String mail = resultSet.getString("mail");
            String password = resultSet.getString("password");
            String nombre = resultSet.getString("nombre");
            String apellidos = resultSet.getString("apellidos");
            return new User(userIndex, mail, password, nombre, apellidos);
        } else {
            return null;
        }
    }

    public List<Red> getRedes(int userId) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        PreparedStatement statement = conn.prepareStatement("SELECT * FROM redes WHERE userId = ?");
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();

        List<Red> listaRedes = new ArrayList<>();

        // Si la query es correcta...
        while(resultSet.next()) {
            int userIndex = resultSet.getInt("userId");
            int redId = resultSet.getInt("redId");
            String mail = resultSet.getString("mail");
            String password = resultSet.getString("password");
            String nombre = resultSet.getString("nom_red");
            listaRedes.add(new Red(redId, userIndex, mail, password, nombre));
        }

        return listaRedes;
    }

    public void addRed(Red red) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        PreparedStatement statement = conn.prepareStatement("INSERT INTO redes values (?, ?, ?, ?, ?)");
        statement.setInt(1, red.getRedId());
        statement.setInt(2, red.getUserId());
        statement.setString(3, red.getMail());
        statement.setString(4, red.getPassword());
        statement.setString(5, red.getNomRed());

        // Ejecutamos la query mediante update
        int rowsInserted = statement.executeUpdate();
    }
}
