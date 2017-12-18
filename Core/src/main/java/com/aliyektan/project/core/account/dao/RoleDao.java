package com.aliyektan.project.core.account.dao;

import com.aliyektan.project.core.connection.MysqlConnection;
import com.aliyektan.project.core.dao.JdbcDao;
import com.aliyektan.project.core.account.entity.Role;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yektan on 16.12.2017.
 * Daha rahat authorization yapılabilmesi için yazılmıştır. Kullaıcıların rolleri
 * temel alınarak ileriye dönük olarak yazılmıştır.
 *
 * NOT: Şuan projede aktif olarak kullanılmamaktadır çünkü kullanıcı işlemleri bu proje için çok ön planda değildir.
 * Fakat ilerisi için bu yapı oluşturulmuştur kullanıma hazırdır.
 */
@ManagedBean
@SessionScoped
public class RoleDao implements JdbcDao<Role, Integer> {

    public Role getById(Integer objectId) throws SQLException , ClassNotFoundException{
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from ROLES WHERE ROLE_ID=" + objectId;
        Role record = new Role();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();
        rs.next();

        record.setRoleId(rs.getInt(1));
        record.setDescription(rs.getString(2));

        return record;
    }

    public void deleteById(Integer objectId) throws SQLException , ClassNotFoundException{
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM ROLES WHERE ROLE_ID=" + objectId;

        pst = con.prepareStatement(stm);
        pst.execute();
    }

    public Integer save(Role object) throws SQLException , ClassNotFoundException{
        int result = 0;
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        con = conn.getConnection();
        pst = con.prepareStatement(
                "insert into ROLES(description) values(?)");
        pst.setString(1, object.getDescription());
        result = pst.executeUpdate();
        con.close();
        return result;
    }

    public void update(Role object) throws SQLException , ClassNotFoundException{

        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        PreparedStatement pst = con.prepareStatement(
                "update ROLES set description=? where role_id=?");
        pst.setString(1, object.getDescription());
        pst.setInt(2, object.getRoleId());
        pst.executeUpdate();
        con.close();

    }

    public void deleteObject(Role object) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM ROLES WHERE ROLE_ID=" + object.getRoleId();


        pst = con.prepareStatement(stm);
        pst.execute();

    }

    public List<Role> findAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from ROLES";
        List<Role> records = new ArrayList<Role>();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            Role role = new Role();
            role.setRoleId(rs.getInt(1));
            role.setDescription(rs.getString(2));
            records.add(role);
        }
        return records;
    }

    public List<Role> findAll(String orderColumn, boolean asc) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from ROLES ORDER BY " + orderColumn;
        if (!asc)
            stm = stm + " DESC";
        List<Role> records = new ArrayList<Role>();


        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            Role role = new Role();
            role.setRoleId(rs.getInt(1));
            role.setDescription(rs.getString(2));
            records.add(role);
        }
        return records;
    }

    public List<Role> findBySql(String sql) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = sql;
        List<Role> records = new ArrayList<Role>();


        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            Role role = new Role();
            role.setRoleId(rs.getInt(1));
            role.setDescription(rs.getString(2));
            records.add(role);
        }

        return records;
    }

}
