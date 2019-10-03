package com.example.test.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private int customerId;
    private String name;
    private String password;


    @OneToMany
    private List<Account> accounts;

    @ManyToMany (mappedBy = "accountHolders")
    private List<Account> accountsWithMultipleAccountHolders;

    public Customer() {
        this("","");
        this.customerId = 0;

    }

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
        this.accounts = new ArrayList<>();
        this.accountsWithMultipleAccountHolders = new ArrayList<>();
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void addNextAccountToCustomer (Account account) {
        accountsWithMultipleAccountHolders.add(account);
    }
}
