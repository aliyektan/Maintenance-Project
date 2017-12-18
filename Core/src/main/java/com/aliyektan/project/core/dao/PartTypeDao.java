package com.aliyektan.project.core.dao;

import com.aliyektan.project.core.entity.PartType;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import static java.lang.Math.toIntExact;

import com.aliyektan.project.core.connection.MysqlConnection;
import org.apache.log4j.Logger;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 * Created by yektan on 16.12.2017.
 */

@SessionScoped
@ManagedBean
public class PartTypeDao implements JdbcDao<PartType, Integer>,Serializable {

    private static final Logger logger = Logger.getLogger(PartTypeDao.class);
    private PartType partType = new PartType();

    public PartType getPartType() {
        return partType;
    }

    public void setPartType(PartType partType) {
        this.partType = partType;
    }

    public PartType getById(Integer objectId) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PART_TYPES WHERE PART_TYPE_ID=" + objectId;
        PartType record = new PartType();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();
        rs.next();

        record.setPartTypeId(rs.getInt(1));
        record.setDescription(rs.getString(2));


        con.close();
        return record;
    }

    public void deleteById(Integer objectId) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM PART_TYPES WHERE PART_TYPE_ID=" + objectId;

        pst = con.prepareStatement(stm);
        pst.execute();
        con.close();
    }

    public Integer save(PartType object) throws SQLException, ClassNotFoundException {
        int result = 0;
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        con = conn.getConnection();
        pst = con.prepareStatement(
                "insert into PART_TYPES(description) values(?)");
        pst.setString(1, object.getDescription());
        result = pst.executeUpdate();
        con.close();
        return result;

    }

    public void update(PartType object) throws SQLException, ClassNotFoundException {

        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        PreparedStatement pst = con.prepareStatement(
                "update PART_TYPES set description=? where part_type_id=?");
        pst.setString(1, object.getDescription());
        pst.setInt(2, object.getPartTypeId());
        pst.executeUpdate();
        con.close();

    }

    public void deleteObject(PartType object) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM PART_TYPES WHERE PART_TYPE_ID=" + object.getPartTypeId();


        pst = con.prepareStatement(stm);
        pst.execute();
        con.close();
    }

    public List<PartType> findAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PART_TYPES";
        List<PartType> records = new ArrayList<PartType>();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            PartType partType = new PartType();
            partType.setPartTypeId(rs.getInt(1));
            partType.setDescription(rs.getString(2));
            records.add(partType);
        }
        con.close();
        return records;
    }

    public List<PartType> findAll(String orderColumn, boolean asc) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PART_TYPES ORDER BY " + orderColumn;
        if (!asc)
            stm = stm + " DESC";
        List<PartType> records = new ArrayList<PartType>();


        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            PartType partType = new PartType();
            partType.setPartTypeId(rs.getInt(1));
            partType.setDescription(rs.getString(2));
            records.add(partType);
        }
        con.close();
        return records;
    }

    public List<PartType> findBySql(String sql) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = sql;
        List<PartType> records = new ArrayList<PartType>();


        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            PartType partType = new PartType();
            partType.setPartTypeId(rs.getInt(1));
            partType.setDescription(rs.getString(2));
            records.add(partType);
        }
        con.close();
        return records;
    }
}
