package com.example.test.controller;

import com.example.test.model.Account;
import com.example.test.model.Customer;
import com.example.test.model.Transaction;
import com.example.test.service.AccountService;
import com.example.test.service.CustomerService;
import com.example.test.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    AccountService accountService;

    @Autowired
    CustomerService customerService;

    @Autowired
    TransactionService transactionService;

    @GetMapping(value = "/customer/{id}")
    public String getCustomer(@PathVariable int id) {
        Customer customer = customerService.findCustomerByCustomerId(id);
        String json = customerService.serialize(customer);
        return json;
    }

    @GetMapping(value = "/account/{id}/{amount}/{password}")
    public String transferMoney (@PathVariable int id, @PathVariable double amount, @PathVariable String password) {
        if (!accountService.accountDoesExists(id)) {
            return "Account bestaat niet";
        }

        Account account = accountService.findAccountByAccountId(id);
        Customer customer = account.getCustomer();

        if (!(customerService.correctPasswordForCustomer(customer,password))) {
            return "Verkeerde wachtwoord ingevoerd";
        }

        if (!accountService.checkForSufficientFunds(account,amount)) {
            return "Saldo te laag";
        }
        Customer bank = customerService.findCustomerByName("Bank");

        if (!(accountService.findAccountByCustomer(bank) == null)) {
            subtractTransferAmount(amount, account);
            addTransferAmount(amount, bank);
            Transaction transaction = transactionService.createPinTransaction(account, amount, bank);
            transactionService.saveTransaction(transaction, "pin");


            return "transfer is gelukt";
        } else {
            return "transfer is niet gelukt, geen ontvangende partij";
        }

    }

    private void addTransferAmount(@PathVariable double amount, Customer bank) {
        Account bankAccount = accountService.findAccountByCustomer(bank);
        double oldBalance = bankAccount.getBalance();
        double bankBalance = oldBalance + amount;
        bankAccount.setBalance(bankBalance);
        accountService.saveAccount(bankAccount);
    }

    private void subtractTransferAmount(@PathVariable double amount, Account account) {
        double balance = account.getBalance();
        double newBalance = balance - amount;
        account.setBalance(newBalance);
        accountService.saveAccount(account);
    }
}
