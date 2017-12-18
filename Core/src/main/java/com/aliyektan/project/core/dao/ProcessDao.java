package com.aliyektan.project.core.dao;

import com.aliyektan.project.core.connection.MysqlConnection;
import com.aliyektan.project.core.entity.Process;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yektan on 16.12.2017.
 */

@ManagedBean
@SessionScoped
public class ProcessDao implements JdbcDao<Process, Integer> {

    private Process process = new Process();

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Process getById(Integer objectId) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PROCESSES WHERE PROCESS_ID=?";
        Process record = new Process();

        pst = con.prepareStatement(stm);
        pst.setInt(1,objectId);
        pst.execute();
        rs = pst.getResultSet();
        rs.next();

        record.setProcessId(rs.getInt(1));
        record.setDescription(rs.getString(2));
        record.setProcessDate(rs.getDate(3));
        record.setNextProcess(rs.getDate(4));
        record.setProductId(rs.getInt(5));
        record.setProcessTypeId(rs.getInt(6));
        record.setChecked(rs.getBoolean(7));
        record.setPartId(rs.getInt(8));


        return record;
    }

    public void deleteById(Integer objectId) throws SQLException, ClassNotFoundException{
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM PROCESSES WHERE PROCESS_ID=" + objectId;


        pst = con.prepareStatement(stm);
        pst.execute();

    }

    public Integer save(Process object) throws SQLException , ClassNotFoundException{
        int result = 0;
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();

        java.util.Date date = object.getProcessDate();
        java.util.Date dateNext = object.getNextProcess();

        con = conn.getConnection();
        pst = con.prepareStatement(
                "insert into PROCESSES(description,process_date,next_process,product_id,process_type_id,checked,part_id) values(?,?,?,?,?,?,?)");
        pst.setString(1, object.getDescription());
        pst.setDate(2, new java.sql.Date(date.getTime()));
        pst.setDate(3, new java.sql.Date(dateNext.getTime()));
        pst.setInt(4, object.getProductId());
        pst.setInt(5, object.getProcessTypeId());
        pst.setBoolean(6, object.isChecked());
        pst.setInt(7, object.getPartId());
        result = pst.executeUpdate();
        con.close();

        return result;
    }

    public void update(Process object) throws SQLException , ClassNotFoundException{
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();

        java.util.Date date = object.getProcessDate();
        java.util.Date dateNext = object.getNextProcess();

        PreparedStatement pst = con.prepareStatement(
                "update PROCESSES set description=?,process_date=?,next_process=?,product_id=?,process_type_id=?,checked=?, part_id where process_id=?");
        pst.setString(1, object.getDescription());
        pst.setDate(2, new java.sql.Date(date.getTime()));
        pst.setDate(3, new java.sql.Date(dateNext.getTime()));
        pst.setInt(4, object.getProductId());
        pst.setInt(5, object.getProcessTypeId());
        pst.setBoolean(6, object.isChecked());
        pst.setInt(7, object.getPartId());
        pst.executeUpdate();
        con.close();
    }

    public void deleteObject(Process object) throws SQLException , ClassNotFoundException{

        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM PROCESSES WHERE PROCESS_ID=" + object.getProcessId();


        pst = con.prepareStatement(stm);
        pst.execute();
        con.close();

    }

    public List<Process> findAll() throws SQLException, ClassNotFoundException {
        System.out.println("FONKSİYONA GELDİ");
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PROCESSES";
        List<Process> records = new ArrayList<Process>();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            Process process = new Process();
            process.setProcessId(rs.getInt(1));
            process.setDescription(rs.getString(2));
            process.setProcessDate(rs.getDate(3));
            process.setNextProcess(rs.getDate(4));
            process.setProductId(rs.getInt(5));
            process.setProcessTypeId(rs.getInt(6));
            process.setChecked(rs.getBoolean(7));
            process.setPartId(rs.getInt(8));
            records.add(process);
        }
        con.close();
        return records;
    }

    public List<Process> findAll(String orderColumn, boolean asc) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PROCESSES";
        List<Process> records = new ArrayList<Process>();
        if (!asc)
            stm = stm + " DESC";

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            Process process = new Process();
            process.setProcessId(rs.getInt(1));
            process.setDescription(rs.getString(2));
            process.setProcessDate(rs.getDate(3));
            process.setNextProcess(rs.getDate(4));
            process.setProductId(rs.getInt(5));
            process.setProcessTypeId(rs.getInt(6));
            process.setChecked(rs.getBoolean(7));
            process.setPartId(rs.getInt(8));
            records.add(process);
        }
        con.close();
        return records;
    }

    public List<Process> findBySql(String sql) throws SQLException , ClassNotFoundException{
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = sql;
        List<Process> records = new ArrayList<Process>();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            Process process = new Process();
            process.setProcessId(rs.getInt(1));
            process.setDescription(rs.getString(2));
            process.setProcessDate(rs.getDate(3));
            process.setNextProcess(rs.getDate(4));
            process.setProductId(rs.getInt(5));
            process.setProcessTypeId(rs.getInt(6));
            process.setChecked(rs.getBoolean(7));
            process.setPartId(rs.getInt(8));
            records.add(process);
        }
        con.close();
        return records;
    }

}
