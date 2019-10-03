package com.example.test.service;

import com.example.test.model.Account;
import com.example.test.model.AccountHolderConfirmationData;
import com.example.test.model.Customer;
import com.example.test.model.dao.AccountHolderConfirmationDataRepository;
import com.example.test.model.dao.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderConfirmationDataRepository accountHolderConfirmationDataRepository;

    public List<Account> findAccountsByCustomer (Customer customer) {
        return accountRepository.findAccountsByCustomer(customer);
    }

    public void saveAccount (Account account) {
        accountRepository.save(account);
    }

    public int openNewAccount (Customer customer) {
        Account account = new Account();
        account.setBalance(100);
        account.setCustomer(customer);
        account.addAccountHolder(customer);
        saveAccount(account);
        return account.getAccountId();
    }

    public Account findAccountByAccountId (int id) {
        return accountRepository.findAccountByAccountId(id);
    }

    public Account findAccountByCustomer (Customer customer) {
        return accountRepository.findAccountByCustomer(customer);
    }

    public boolean accountDoesExists (int id) {
        return accountRepository.existsById(id);
    }

    public boolean checkForSufficientFunds (Account account, double transferAmount) {
        return account.getBalance() > transferAmount;
    }

    public List<Customer> findAllAccountHoldersByAccountId (int accountId) {
        Account account = findAccountByAccountId(accountId);
        List<Customer> accountHolders = account.getAccountHolders();
        return accountHolders;
    }

    public void saveAccountHolderConfirmationData (String nameNewAccountHolder, int accountId, int securityCode) {
        AccountHolderConfirmationData confirmationData = new AccountHolderConfirmationData();
        confirmationData.setAccountHolderName(nameNewAccountHolder);
        confirmationData.setAccountNumber(accountId);
        confirmationData.setSecurityCode(securityCode);
        accountHolderConfirmationDataRepository.save(confirmationData);
    }

    public double getTotalBalanceOfBank () {
        double totalBalance = 0;
        List<Account> allAccounts = accountRepository.findAll();
        for (int i = 0; i < allAccounts.size(); i++) {
            totalBalance += allAccounts.get(i).getBalance();
        }
        return totalBalance;
    }





}
