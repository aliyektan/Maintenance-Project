package com.aliyektan.project.core.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yektan on 16.12.2017.
 * Data Access Object Classlarımın temeli, interface'i tüm DAO classlarım burdan
 * türetilmiş ve implemente edilmiştir. Tüm sınıflara rahatça implemente
 * edilebilmesi için <T,Id> değişkenleri tanımlanmıştır.
 *
 */
public interface JdbcDao<T, Id extends Serializable> extends Serializable {

    public T getById(Id objectId) throws SQLException, ClassNotFoundException;

    public void deleteById(Id objectId) throws SQLException, ClassNotFoundException;

    public Id save(T object) throws SQLException, ClassNotFoundException;

    public void update(T object) throws SQLException, ClassNotFoundException;

    public void deleteObject(T object) throws SQLException, ClassNotFoundException;

    public List<T> findAll() throws SQLException, ClassNotFoundException;

    public List<T> findAll(final String orderColumn, boolean asc) throws SQLException, ClassNotFoundException;

    public List<T> findBySql(String sql) throws SQLException, ClassNotFoundException;

}
