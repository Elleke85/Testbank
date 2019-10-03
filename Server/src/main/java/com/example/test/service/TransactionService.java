package com.example.test.service;

import com.example.test.model.Account;
import com.example.test.model.Customer;
import com.example.test.model.Transaction;
import com.example.test.model.dao.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    public Transaction createPinTransaction (Account account, double amount, Customer receiver) {
        Transaction pinTransaction = new Transaction();
        pinTransaction.setDescription("pinTransactie naar Bank");
        pinTransaction.setFromAccountId(account.getAccountId());
        pinTransaction.setToAccountName(receiver.getName());
        pinTransaction.setToAccountId(accountService.findAccountByCustomer(receiver).getAccountId());
        pinTransaction.setAmount(amount);

        return pinTransaction;
    }

    public void saveTransaction(Transaction transaction, String typeOfTransfer) {
        Date date = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        transaction.setDate(dateFormatter.format(date));
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
        transaction.setTime(timeFormatter.format(date));

        transaction.setTypeOfTransfer(typeOfTransfer);
        transaction.setFromAccountName(accountService.findAccountByAccountId(transaction.getFromAccountId()).getCustomer().getName());
        transactionRepository.save(transaction);
    }


    public List<Transaction> showTransactions (Account account) {
        List<Transaction> creditTransactions = transactionRepository.findTransactionsByFromAccountId(account.getAccountId());
        List<Transaction> debetTransactions = transactionRepository.findTransactionsByToAccountId(account.getAccountId());

        List<Transaction> totalTransactions = new ArrayList<>();
        totalTransactions.addAll(creditTransactions);
        totalTransactions.addAll(debetTransactions);
        totalTransactions.sort(Comparator.comparing(Transaction :: getTransferNr).reversed());

        return totalTransactions;

    }





}
