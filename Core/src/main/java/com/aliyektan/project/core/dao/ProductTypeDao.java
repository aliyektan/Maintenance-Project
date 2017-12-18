package com.aliyektan.project.core.dao;

import com.aliyektan.project.core.connection.MysqlConnection;
import com.aliyektan.project.core.entity.ProductType;

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
@ManagedBean(name = "productTypeDao")
public class ProductTypeDao implements JdbcDao<ProductType, Integer> {

    private ProductType productType = new ProductType();

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ProductType getById(Integer objectId) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PRODUCT_TYPES WHERE PRODUCT_TYPE_ID=?";
        ProductType record = new ProductType();


            pst = con.prepareStatement(stm);
            pst.setInt(1,objectId);
            pst.execute();
            rs = pst.getResultSet();

            record.setProductTypeId(rs.getInt(1));
            record.setDescription(rs.getString(2));

        return record;
    }

    public void deleteById(Integer objectId) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM PRODUCT_TYPES WHERE PRODUCT_TYPE_ID=" + objectId;


        pst = con.prepareStatement(stm);
        pst.execute();
        con.close();
    }

    public Integer save(ProductType object) throws SQLException, ClassNotFoundException {
        int result = 0;
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();

        con = conn.getConnection();
        pst = con.prepareStatement(
                "insert into PRODUCT_TYPES(description) values(?)");
        pst.setString(1, object.getDescription());
        result = pst.executeUpdate();
        con.close();

        return result;
    }

    public void update(ProductType object) throws SQLException, ClassNotFoundException {


        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        PreparedStatement pst = con.prepareStatement(
                "update PRODUCT_TYPES set description=? where product_type_id=?");
        pst.setString(1, object.getDescription());
        pst.setInt(2, object.getProductTypeId());
        pst.executeUpdate();
        con.close();


    }

    public void deleteObject(ProductType object) throws SQLException, ClassNotFoundException {
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "DELETE FROM PRODUCT_TYPES WHERE PRODUCT_TYPE_ID=" + object.getProductTypeId();


        pst = con.prepareStatement(stm);
        pst.execute();
        con.close();

    }

    public List<ProductType> findAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PRODUCT_TYPES";
        List<ProductType> records = new ArrayList<ProductType>();


        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            ProductType partType = new ProductType();
            partType.setProductTypeId(rs.getInt(1));
            partType.setDescription(rs.getString(2));
            records.add(partType);
        }
        con.close();
        return records;
    }

    public List<ProductType> findAll(String orderColumn, boolean asc) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = "Select * from PRODUCT_TYPES ORDER BY " + orderColumn;
        if (!asc)
            stm = stm + " DESC";
        List<ProductType> records = new ArrayList<ProductType>();


        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            ProductType partType = new ProductType();
            partType.setProductTypeId(rs.getInt(1));
            partType.setDescription(rs.getString(2));
            records.add(partType);
        }
        con.close();
        return records;
    }

    public List<ProductType> findBySql(String sql) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pst = null;
        MysqlConnection conn = new MysqlConnection();
        Connection con = conn.getConnection();
        String stm = sql;
        List<ProductType> records = new ArrayList<ProductType>();


        pst = con.prepareStatement(stm);
        pst.execute();
        rs = pst.getResultSet();

        while (rs.next()) {
            ProductType partType = new ProductType();
            partType.setProductTypeId(rs.getInt(1));
            partType.setDescription(rs.getString(2));
            records.add(partType);
        }
        con.close();
        return records;
    }
}
