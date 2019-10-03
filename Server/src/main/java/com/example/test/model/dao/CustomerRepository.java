package com.example.test.model.dao;

import com.example.test.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findCustomerByName(String name);
    Customer findCustomerByCustomerId(int id);
    Customer findCustomerByPassword(String password);
    boolean existsByName(String name);



}
