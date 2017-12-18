package com.aliyektan.project.core.dao;

import com.aliyektan.project.core.connection.MysqlConnection;
import com.aliyektan.project.core.entity.Part;

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
 */
@SessionScoped
@ManagedBean(name = "partDao")
public class PartDao implements JdbcDao<Part, Integer> {

    private Part part = new Part();

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Part getById(Integer objectId) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PARTS WHERE PART_ID=" + objectId;
        Part record = new Part();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();
        rs.next();

        record.setPartTypeId(rs.getInt(1));
        record.setDescription(rs.getString(2));
        record.setPartTypeId(rs.getInt(3));
        con.close();
        return record;
    }

    public void deleteById(Integer objectId) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM PARTS WHERE PART_ID=" + objectId;

        pst = con.prepareStatement(stm);
        pst.execute();
        con.close();
    }

    public Integer save(Part object) throws SQLException, ClassNotFoundException {
        int result = 0;
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        con = conn.getConnection();
        pst = con.prepareStatement(
                "insert into PARTS(description,part_type_id) values(?,?)");
        pst.setString(1, object.getDescription());
        pst.setInt(2, object.getPartTypeId());
        result = pst.executeUpdate();
        con.close();
        return result;
    }

    public void update(Part object) throws SQLException , ClassNotFoundException{

        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        PreparedStatement pst = con.prepareStatement(
                "update PARTS set description=?,part_type_id=? where part_id=?");
        pst.setString(1, object.getDescription());
        pst.setInt(2, object.getPartTypeId());
        pst.setInt(3, object.getPartId());
        pst.executeUpdate();
        con.close();

    }

    public void deleteObject(Part object) throws SQLException , ClassNotFoundException{
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM PARTS WHERE PART_ID=" + object.getPartId();


        pst = con.prepareStatement(stm);
        pst.execute();
        con.close();

    }

    public List<Part> findAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PARTS";
        List<Part> records = new ArrayList<Part>();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            Part part = new Part();
            part.setPartId(rs.getInt(1));
            part.setDescription(rs.getString(2));
            part.setPartTypeId(rs.getInt(3));
            records.add(part);
        }
        con.close();
        return records;
    }

    public List<Part> findAll(String orderColumn, boolean asc) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PARTS ORDER BY " + orderColumn;
        if (!asc)
            stm = stm + " DESC";
        List<Part> records = new ArrayList<Part>();


        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            Part part = new Part();
            part.setPartId(rs.getInt(1));
            part.setDescription(rs.getString(2));
            part.setPartTypeId(rs.getInt(3));
            records.add(part);
        }
        con.close();
        return records;
    }

    public List<Part> findBySql(String sql) throws SQLException , ClassNotFoundException{
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = sql;
        List<Part> records = new ArrayList<Part>();


        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            Part part = new Part();
            part.setPartId(rs.getInt(1));
            part.setDescription(rs.getString(2));
            part.setPartTypeId(rs.getInt(3));
            records.add(part);
        }
        con.close();
        return records;
    }

}
