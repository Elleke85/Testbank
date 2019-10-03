package com.example.test.model.dao;

import com.example.test.model.Account;
import com.example.test.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    List<Account> findAccountsByCustomer(Customer customer);

    Account findAccountByAccountId(int id);

    Account findAccountByCustomer(Customer customer);

    boolean existsById(int id);

    boolean existsByCustomer (Customer customer);

    List<Account> findAll ();







}
