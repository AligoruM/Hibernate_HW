package com.netcracker.dao.customer;

import com.netcracker.model.Customer;

import java.util.List;

public interface ICustomerDAO {
    void saveCustomer(Customer entity);

    List<Customer> findAllCustomers();

    Customer findCustomerById(int id);

    void deleteCustomerById(int id);

    long rowCount();

    List<String> task2b();

    List<Customer> findCustomerByDistrict(String district);

}
