package com.example.test.service;

import com.example.test.model.Account;
import com.example.test.model.Customer;
import com.example.test.model.dao.CustomerRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void saveCustomer (Customer customer) {
        customerRepository.save(customer);
    }

    public boolean existsByName (String name) {
        return customerRepository.existsByName(name);
    }

    public Customer findCustomerByName (String name) {
        return customerRepository.findCustomerByName(name);
    }

    public Customer findCustomerByCustomerId (int id)  {
        return customerRepository.findCustomerByCustomerId(id);
    }

    public String serialize(Customer customer) {
        Gson gson = new Gson(); // in pom.xml, import additionele dependency voor toegang tot com.google.gson.Gson;
        String json = gson.toJson(customer);
        return json;
    }

    public boolean correctPasswordForCustomer (Customer customer, String password) {
        Customer customerFoundByPassword = customerRepository.findCustomerByPassword(password);
        return customer.equals(customerFoundByPassword);

    }

    public void addAccountHolderToExistingAccount (Customer customer, Account account) {
        customer.addNextAccountToCustomer(account);
        account.addAccountHolder(customer);
    }


}
