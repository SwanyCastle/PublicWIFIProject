package com.wifi.publicwifiproject.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection connectDB() {
        Connection connection = null;
        String url = "jdbc:mariadb://localhost:3306/public_wifi";
        String dbUserId = "root";
        String dbUserPwd = "root";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbUserPwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
