package com.aliyektan.project.core.dao;

import com.aliyektan.project.core.connection.MysqlConnection;
import com.aliyektan.project.core.entity.ProcessType;

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
@ManagedBean(name = "processTypeDao")
public class ProcessTypeDao implements JdbcDao<ProcessType, Integer> {

    private ProcessType processType = new ProcessType();

    public ProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

    public ProcessType getById(Integer objectId) throws SQLException , ClassNotFoundException{
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PROCESS_TYPES WHERE PROCESS_TYPE_ID=" + objectId;
        ProcessType record = new ProcessType();


        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();
        rs.next(); //TEST BURAYI SAKIN UNUTMA

        record.setProcessTypeId(rs.getInt(1));
        record.setDescription(rs.getString(2));
        con.close();
        return record;
    }

    public void deleteById(Integer objectId) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM PROCESS_TYPES WHERE PROCESS_TYPE_ID=" + objectId;


        pst = con.prepareStatement(stm);
        pst.execute();
        con.close();

    }

    public Integer save(ProcessType object) throws SQLException , ClassNotFoundException{
        int result = 0;
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();

        con = conn.getConnection();
        pst = con.prepareStatement(
                "insert into PROCESS_TYPES(description) values(?)");
        pst.setString(1, object.getDescription());
        result = pst.executeUpdate();

        con.close();
        return result;
    }

    public void update(ProcessType object) throws SQLException , ClassNotFoundException{


        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        PreparedStatement pst = con.prepareStatement(
                "update PROCESS_TYPES set description=? where process_type_id=?");
        pst.setString(1, object.getDescription());
        pst.setInt(2, object.getProcessTypeId());
        pst.executeUpdate();
        con.close();


    }

    public void deleteObject(ProcessType object) throws SQLException , ClassNotFoundException{
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM PROCESS_TYPES WHERE PROCESS_TYPE_ID=" + object.getProcessTypeId();

        pst = con.prepareStatement(stm);
        pst.execute();
        con.close();

    }

    public List<ProcessType> findAll() throws SQLException , ClassNotFoundException{
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PROCESS_TYPES";
        List<ProcessType> records = new ArrayList<ProcessType>();


        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            ProcessType partType = new ProcessType();
            partType.setProcessTypeId(rs.getInt(1));
            partType.setDescription(rs.getString(2));
            records.add(partType);
        }
        con.close();
        return records;
    }

    public List<ProcessType> findAll(String orderColumn, boolean asc) throws SQLException , ClassNotFoundException{
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PROCESS_TYPES ORDER BY " + orderColumn;
        if (!asc)
            stm = stm + " DESC";
        List<ProcessType> records = new ArrayList<ProcessType>();


        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            ProcessType partType = new ProcessType();
            partType.setProcessTypeId(rs.getInt(1));
            partType.setDescription(rs.getString(2));
            records.add(partType);
        }
        con.close();
        return records;
    }

    public List<ProcessType> findBySql(String sql) throws SQLException , ClassNotFoundException{
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = sql;
        List<ProcessType> records = new ArrayList<ProcessType>();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            ProcessType partType = new ProcessType();
            partType.setProcessTypeId(rs.getInt(1));
            partType.setDescription(rs.getString(2));
            records.add(partType);
        }
        con.close();
        return records;
    }
}
