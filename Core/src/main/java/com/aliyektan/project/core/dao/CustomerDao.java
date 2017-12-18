package com.aliyektan.project.core.dao;

import com.aliyektan.project.core.connection.MysqlConnection;
import com.aliyektan.project.core.entity.Customer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by yektan on 16.12.2017.
 */

@ManagedBean(name = "customerDao")
@SessionScoped
public class CustomerDao implements JdbcDao<Customer, Integer> {

    private Customer customer = new Customer();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getById(Integer objectId) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from CUSTOMERS WHERE CUSTOMER_ID=" + objectId;
        Customer record = new Customer();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();
        rs.next();

        record.setCustomerId(rs.getInt(1));
        record.setName(rs.getString(2));
        record.setSurname(rs.getString(3));
        record.setEmail(rs.getString(4));
        record.setAddress(rs.getString(5));
        record.setPhoneNumber(rs.getString(6));
        con.close();
        return record;
    }

    public void deleteById(Integer objectId) throws SQLException , ClassNotFoundException{
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID=" + objectId;


        pst = con.prepareStatement(stm);
        pst.execute();
        con.close();

    }

    public Integer save(Customer object) throws SQLException, ClassNotFoundException {
        int result = 0;
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();

        con = conn.getConnection();
        pst = con.prepareStatement(
                "insert into CUSTOMERS(customer_name,customer_surname,email,address,phonenumber) values(?,?,?,?,?)");
        pst.setString(1, object.getName());
        pst.setString(2, object.getSurname());
        pst.setString(3, object.getEmail());
        pst.setString(4, object.getAddress());
        pst.setString(5, object.getPhoneNumber());
        result = pst.executeUpdate();
        con.close();

        return result;
    }

    public void update(Customer object) throws SQLException , ClassNotFoundException{
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        PreparedStatement pst = con.prepareStatement(
                "update CUSTOMERS set customer_name=?,customer_surname=?,email=?,address=?,phone_number=? where customer_id=?");
        pst.setString(1, object.getName());
        pst.setString(2, object.getSurname());
        pst.setString(3, object.getEmail());
        pst.setString(4, object.getAddress());
        pst.setString(5, object.getPhoneNumber());
        pst.executeUpdate();
        con.close();
    }

    public void deleteObject(Customer object) throws SQLException , ClassNotFoundException{

        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID=" + object.getCustomerId();


        pst = con.prepareStatement(stm);
        pst.execute();
        con.close();

    }

    public List<Customer> findAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from CUSTOMERS";
        List<Customer> records = new ArrayList<Customer>();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt(1));
            customer.setName(rs.getString(2));
            customer.setSurname(rs.getString(3));
            customer.setEmail(rs.getString(4));
            customer.setAddress(rs.getString(5));
            customer.setPhoneNumber(rs.getString(6));
            records.add(customer);
        }
        con.close();
        return records;
    }

    public List<Customer> findAll(String orderColumn, boolean asc) throws SQLException , ClassNotFoundException{
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from CUSTOMERS";
        List<Customer> records = new ArrayList<Customer>();
        if (!asc)
            stm = stm + " DESC";

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt(1));
            customer.setName(rs.getString(2));
            customer.setSurname(rs.getString(3));
            customer.setEmail(rs.getString(4));
            customer.setAddress(rs.getString(5));
            customer.setPhoneNumber(rs.getString(6));
            records.add(customer);
        }
        con.close();
        return records;
    }

    public List<Customer> findBySql(String sql) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = sql;
        List<Customer> records = new ArrayList<Customer>();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            Customer customer = new Customer();
            customer.setCustomerId(rs.getInt(1));
            customer.setName(rs.getString(2));
            customer.setSurname(rs.getString(3));
            customer.setEmail(rs.getString(4));
            customer.setAddress(rs.getString(5));
            customer.setPhoneNumber(rs.getString(6));
            records.add(customer);
        }
        con.close();
        return records;
    }
}
