package com.aliyektan.project.core.account.dao;

import com.aliyektan.project.core.connection.MysqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yektan on 17.12.2017.
 * valdate() fonskiyonu aldığı parametreleri databaseden çeker boolean tipinde return yapar.
 */
public class LoginDao {

    public static boolean validate(String email, String password) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        MysqlConnection connection = new MysqlConnection();


            con = connection.getConnection();
            ps = con.prepareStatement("Select email, passwd from Users where email = ? and passwd = ?");
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //result found, means valid inputs
                return true;
            }
            con.close();
        return false;
    }
}
