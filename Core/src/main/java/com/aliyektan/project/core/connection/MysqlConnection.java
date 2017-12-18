package com.aliyektan.project.core.connection;

import com.aliyektan.project.core.dao.PartTypeDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by yektan on 16.12.2017.
 */
public class MysqlConnection {


    /* JDBC MYSQL BAĞLANTISI YAPAN CLASS */
    public Connection getConnection() throws SQLException, ClassNotFoundException {
         Class.forName("com.mysql.jdbc.Driver");
         Connection conn = null;
         String dbURL = "jdbc:mysql://localhost:3306/MYDATABASE";
         String username = "root";
         String password = "root";

        conn = DriverManager.getConnection(dbURL, username, password);
        if (conn == null) {
            System.out.println("NOT CONNECTED "+ System.currentTimeMillis());
        }
        return conn;
    }

}
