package com.aliyektan.project.core.dao;

import com.aliyektan.project.core.entity.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yektan on 17.12.2017.
 */
public class CustomerDaoTest {

    CustomerDao customerDao = new CustomerDao();

    @Test
    public void getById() throws Exception {

        Customer customer = customerDao.getById(1);
        Customer testCustomer = new Customer();
        testCustomer.setCustomerId(1);
        assertEquals(customer.getCustomerId(),testCustomer.getCustomerId());
    }


}