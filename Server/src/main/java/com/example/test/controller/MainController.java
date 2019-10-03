package com.example.test.controller;

import com.example.test.model.Account;
import com.example.test.model.Customer;
import com.example.test.model.Transaction;
import com.example.test.service.AccountService;
import com.example.test.service.CustomerService;
import com.example.test.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes ({"id", "name", "password", "accountId"})
public class MainController {

    @Autowired
    CustomerService customerService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @GetMapping (value = "/")
    public String homepage () {
        System.out.println("start homepage Handler");
        // fill database met bank as Customer with account
        if (customerService.existsByName("Bank")) {
            return "Index";
        }
        Customer bankCustomer = new Customer();
        bankCustomer.setName("Bank");
        bankCustomer.setPassword("bank");
        customerService.saveCustomer(bankCustomer);
        accountService.openNewAccount(bankCustomer);

        return "Index";
    }


    @GetMapping(value = "register")
    public String registerClient () {
        Customer customer = new Customer();
        return "Registration";
    }

    @PostMapping(value = "registrationConfirmation")
    public String registerConfirmationClient (Customer customer, Model model) {
        customerService.saveCustomer(customer);
        model.addAttribute("id", customer.getCustomerId());
        return "RegistrationConfirmation";
    }

    @GetMapping(value = "login")
    public String loginClient () {
//
        return "Login";
    }

    @PostMapping(value = "overview")
    public String overviewClient (@ModelAttribute Customer customer, Model model) {
        Customer c = customerService.findCustomerByName(customer.getName());
        model.addAttribute("name", c.getName());
        model.addAttribute("id", c.getCustomerId());
        model.addAttribute("password", c.getPassword());
        List<Account> accounts = new ArrayList<>();
        if (customerService.existsByName(c.getName())) {
            accounts = accountService.findAccountsByCustomer(c);
            model.addAttribute("accounts", accounts);
            return "Overview";
        }
        return "Error";
    }

    @GetMapping (value = "overviewWithName")
    public String overviewWithName (@SessionAttribute ("name") String name, Model model) {
        Customer customer = customerService.findCustomerByName(name);
        overviewClient(customer,model);
        return "Overview";
    }

    @PostMapping(value = "openAccountConfirmation")
    public String accountConfirmation (@SessionAttribute ("name") String name, Model model) {
        Customer customer = customerService.findCustomerByName(name);
        int accountId = accountService.openNewAccount(customer);
        model.addAttribute("accountId", accountId);
        return "OpenAccountConfirmation";
    }

    @GetMapping (value = "accountSummary")
    public String accountSummary (@RequestParam int accountId, Model model) {
        Account account = accountService.findAccountByAccountId(accountId);
        model.addAttribute("balance", account.getBalance());
        model.addAttribute ("accountId", accountId);
        List<Transaction> allTransactions = transactionService.showTransactions(account);
        model.addAttribute("allTransactions", allTransactions);
        return "AccountSummary";
    }

    @GetMapping(value = "transaction")
    public String transfer ( Model model) {
        model.containsAttribute("name");
        model.containsAttribute("accountId");
        Transaction transaction = new Transaction();
        return "Transaction";
    }

    @PostMapping(value = "transactionConfirmation")
    public String transferConfirmation (@ModelAttribute Transaction transaction, @SessionAttribute ("accountId") int accountId, @SessionAttribute ("name") String name, Model model) {
        Account creditAccount = accountService.findAccountByAccountId(accountId);
        Account debetAccount = accountService.findAccountByAccountId(transaction.getToAccountId());

        boolean sufficientFunds = accountService.checkForSufficientFunds(creditAccount, transaction.getAmount());
        boolean correctReceiver = accountService.accountDoesExists(debetAccount.getAccountId());

        if (sufficientFunds && correctReceiver){
            transaction.setFromAccountId(creditAccount.getAccountId());
            transactionService.saveTransaction(transaction,"manualTransfer");
            creditAccount.setBalance(creditAccount.getBalance()-transaction.getAmount());
            accountService.saveAccount(creditAccount);
            debetAccount.setBalance(debetAccount.getBalance() + transaction.getAmount());
            accountService.saveAccount(debetAccount);
        }
        model.addAttribute("amount", transaction.getAmount());
        model.addAttribute("accountId");
        return "TransactionConfirmation";
    }

    @GetMapping (value = "addAccountHolder")
    public String addAccountHolder (@SessionAttribute ("accountId") int accountId, Model model) {
        model.containsAttribute("name");
        model.containsAttribute("accountId");

        List<Customer> accountHolders = accountService.findAllAccountHoldersByAccountId(accountId);
        model.addAttribute("accountHolders", accountHolders);

        return "AddAccountHolder";
    }

    @PostMapping (value = "addAccountHolderConfirmation")
    public String addAccountHolderConfirmation (@SessionAttribute ("accountId") int accountId, @RequestParam (value = "nameNewAccountHolder") String nameNewAccountHolder , @RequestParam(value = "securityCode") int securityCode, Model model) {
        model.addAttribute("securityCode", securityCode);
        model.containsAttribute("name");
        model.containsAttribute("accountId");
        model.addAttribute("newAccountHolder", nameNewAccountHolder);
        accountService.saveAccountHolderConfirmationData(nameNewAccountHolder, accountId,securityCode);

        return "AddAccountHolderConfirmation";
    }
}
