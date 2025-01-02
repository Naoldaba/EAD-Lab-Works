package com.itsc.exam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class DBConnectionManager {
    private String url;
    private String username;
    private String password;

    public Connection openConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}