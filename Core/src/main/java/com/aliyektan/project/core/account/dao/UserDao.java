package com.aliyektan.project.core.account.dao;

import com.aliyektan.project.core.connection.MysqlConnection;
import com.aliyektan.project.core.dao.JdbcDao;
import com.aliyektan.project.core.account.util.SessionUtils;
import com.aliyektan.project.core.account.entity.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yektan on 16.12.2017.
 * User sınıfım için gerekli dao fonsiyonları diğer dao classları gibi 'JdbcDao' interface'inden türetildi.
 * Sonraki geliştirmeler için sıkıntı olmaması açısından 'findBySql()' fonskiyonu yazıldı.
 */
@SessionScoped
@ManagedBean(name = "userDao")
public class UserDao implements JdbcDao<User, Integer> {

    private User user = new User();
    private RoleDao roleDao = new RoleDao();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getById(Integer objectId) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from USERS WHERE USER_ID=" + objectId;
        User record = new User();


        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();
        rs.next();

        record.setUserId(rs.getInt(1));
        record.setName(rs.getString(2));
        record.setSurname(rs.getString(3));
        record.setPassword(rs.getString(4));
        record.setEmail(rs.getString(5));
        record.setRoleId(rs.getInt(6));


        return record;
    }

    public void deleteById(Integer objectId) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM USERS WHERE USER_ID=" + objectId;


        pst = con.prepareStatement(stm);
        pst.execute();

    }

    public Integer save(User object) throws SQLException, ClassNotFoundException {
        int result = 0;
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();

        con = conn.getConnection();
        pst = con.prepareStatement(
                "insert into USERS(user_name,user_surname,passwd,email,phonenumber,role_id) values(?,?,?,?,?,?,?)");
        pst.setString(1, object.getName());
        pst.setString(2, object.getSurname());
        pst.setString(3, object.getPassword());
        pst.setString(5, object.getEmail());
        pst.setString(6, object.getPassword());
        pst.setInt(7, object.getRoleId());
        result = pst.executeUpdate();
        con.close();

        return result;
    }

    public void update(User object) throws SQLException, ClassNotFoundException {
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        PreparedStatement pst = con.prepareStatement(
                "update USERS set user_name=?,user_surname=?,passwd=?,email=?,phone_number=?,role_id=? where user_id=?");
        pst.setString(1, object.getName());
        pst.setString(2, object.getSurname());
        pst.setString(3, object.getEmail());
        pst.setString(5, object.getEmail());
        pst.setString(6, object.getPhoneNumber());
        pst.setInt(7, object.getRoleId());
        pst.executeUpdate();
        con.close();
    }

    public void deleteObject(User object) throws SQLException, ClassNotFoundException {

        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM USERS WHERE USER_ID=" + object.getUserId();


        pst = con.prepareStatement(stm);
        pst.execute();

    }

    public List<User> findAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from USERS";
        List<User> records = new ArrayList<User>();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setSurname(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setEmail(rs.getString(5));
            user.setPhoneNumber(rs.getString(6));
            user.setRoleId(rs.getInt(7));
            records.add(user);
        }
        return records;
    }

    public List<User> findAll(String orderColumn, boolean asc) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from USERS";
        List<User> records = new ArrayList<User>();
        if (!asc)
            stm = stm + " DESC";

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setSurname(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setEmail(rs.getString(5));
            user.setPhoneNumber(rs.getString(6));
            user.setRoleId(rs.getInt(7));
            records.add(user);
        }
        return records;
    }

    public List<User> findBySql(String sql) throws SQLException , ClassNotFoundException{
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = sql;
        List<User> records = new ArrayList<User>();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setSurname(rs.getString(3));
            user.setPassword(rs.getString(4));
            user.setEmail(rs.getString(5));
            user.setPhoneNumber(rs.getString(6));
            user.setRoleId(rs.getInt(7));
            records.add(user);
        }
        return records;
    }


    public String validateUser() throws SQLException, ClassNotFoundException {

        boolean valid = LoginDao.validate(user.getEmail(), user.getPassword());
        if (valid) {
            HttpSession session = SessionUtils.getSession();
            user = findBySql("select * from users where email='"+user.getEmail()+"' and passwd='"+user.getPassword()+"'").get(0);
            session.setAttribute("email", user.getEmail());
            session.setAttribute("name", user.getName());
            session.setAttribute("surname", user.getSurname());
            session.setAttribute("role",roleDao.getById(user.getRoleId()).getDescription());
            return "main";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Email and Password",
                            "Please enter correct Email and Password"));
            return "login";
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        user=null;
        return "login";
    }



}
