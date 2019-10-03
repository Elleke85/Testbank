package com.example.test.service;

import com.example.test.model.Customer;
import com.example.test.model.dao.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void saveCustomerTest1() {
        Customer test1 = new Customer();
        test1.setName("test1");
        test1.setPassword("1");
        customerRepository.save(test1);

        // check if correctly saved
        boolean expected = true;
        boolean actual = customerRepository.existsByName("test1");

        assertEquals(expected,actual);
    }

    @Test
    void saveCustomerTest2() {
        Customer test1 = new Customer();
        test1.setName("test2");
        test1.setPassword("2");
        customerRepository.save(test1);

        // check if correctly saved
        boolean expected = true;
        boolean actual = customerRepository.existsByName("test2");

        assertEquals(expected,actual);
    }

    @Test
    void findCustomerByName() {
        // test will fail if test SaveCustomerTest1 has not run before
        String expectedPassword = "1";
        String actualPassword = customerRepository.findCustomerByName("test1").getPassword();
        assertEquals(expectedPassword,actualPassword);
    }
}