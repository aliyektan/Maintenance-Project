package com.aliyektan.project.core.dao;

import com.aliyektan.project.core.connection.MysqlConnection;
import com.aliyektan.project.core.entity.Product;

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
@ManagedBean(name = "productDao")
public class ProductDao implements JdbcDao<Product, Integer> {

    private Product product = new Product();

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getById(Integer objectId) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PRODUCTS WHERE PRODUCT_ID=" + objectId;
        Product record = new Product();


        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();
        rs.next();

        record.setProductId(rs.getInt(1));
        record.setDescription(rs.getString(2));
        record.setSerialNumber(rs.getString(3));
        record.setBrand(rs.getString(4));
        record.setModel(rs.getString(5));
        record.setCustomerId(rs.getInt(6));
        record.setProductTypeId(rs.getInt(7));
        con.close();

        return record;
    }

    public void deleteById(Integer objectId) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM PRODUCTS WHERE PRODUCT_ID=" + objectId;


        pst = con.prepareStatement(stm);
        pst.execute();
        con.close();

    }

    public Integer save(Product object) throws SQLException, ClassNotFoundException {
        int result = 0;
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();

        con = conn.getConnection();
        pst = con.prepareStatement(
                "insert into PRODUCTS(description,serial_number,brand,model,customer_id,product_type_id) values(?,?,?,?,?,?)");
        pst.setString(1, object.getDescription());
        pst.setString(2, object.getSerialNumber());
        pst.setString(3, object.getBrand());
        pst.setString(4, object.getModel());
        pst.setInt(5, object.getCustomerId());
        pst.setInt(6, object.getProductTypeId());
        result = pst.executeUpdate();
        con.close();

        return result;
    }

    public void update(Product object) throws SQLException, ClassNotFoundException {
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        PreparedStatement pst = con.prepareStatement(
                "update PRODUCTS set description=?,serial_number=?,brand=?,model=?,customer_id=?,product_type_id=? where product_id=?");
        pst.setString(1, object.getDescription());
        pst.setString(2, object.getSerialNumber());
        pst.setString(3, object.getBrand());
        pst.setString(4, object.getModel());
        pst.setInt(5, object.getCustomerId());
        pst.setInt(6, object.getProductTypeId());
        pst.executeUpdate();
        con.close();
    }

    public void deleteObject(Product object) throws SQLException, ClassNotFoundException {

        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM PRODUCTS WHERE PRODUCT_ID=" + object.getProductId();


        pst = con.prepareStatement(stm);
        pst.execute();
        con.close();

    }

    public List<Product> findAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PRODUCTS";
        List<Product> records = new ArrayList<Product>();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            Product product = new Product();
            product.setProductId(rs.getInt(1));
            product.setDescription(rs.getString(2));
            product.setSerialNumber(rs.getString(3));
            product.setBrand(rs.getString(4));
            product.setModel(rs.getString(5));
            product.setCustomerId(rs.getInt(6));
            product.setProductTypeId(rs.getInt(7));
            records.add(product);
        }
        con.close();
        return records;
    }

    public List<Product> findAll(String orderColumn, boolean asc) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PRODUCTS";
        List<Product> records = new ArrayList<Product>();
        if (!asc)
            stm = stm + " DESC";

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            Product product = new Product();
            product.setProductId(rs.getInt(1));
            product.setDescription(rs.getString(2));
            product.setSerialNumber(rs.getString(3));
            product.setBrand(rs.getString(4));
            product.setModel(rs.getString(5));
            product.setCustomerId(rs.getInt(6));
            product.setProductTypeId(rs.getInt(7));
            records.add(product);
        }
        con.close();
        return records;
    }

    public List<Product> findBySql(String sql) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = sql;
        List<Product> records = new ArrayList<Product>();

        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            Product product = new Product();
            product.setProductId(rs.getInt(1));
            product.setDescription(rs.getString(2));
            product.setSerialNumber(rs.getString(3));
            product.setBrand(rs.getString(4));
            product.setModel(rs.getString(5));
            product.setCustomerId(rs.getInt(6));
            product.setProductTypeId(rs.getInt(7));
            records.add(product);
        }
        con.close();
        return records;
    }

}
