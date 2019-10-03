package com.example.test.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private int accountId;
    private double balance;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    private List<Customer> accountHolders;

    public Account() {
        this(0,0);
    }

    public Account(int accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.accountHolders = new ArrayList<>();
    }

    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public List<Customer> getAccountHolders() {
        return accountHolders;
    }
    public void setAccountHolders(List<Customer> accountHolders) {
        this.accountHolders = accountHolders;
    }



    public void addAccountHolder (Customer customer) {
        accountHolders.add(customer);
    }
}
