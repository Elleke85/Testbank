package com.example.test.service;

import com.example.test.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    void saveTransaction() {
        Transaction transaction = new Transaction();

        // total balance of bank before transaction
        double totalBalanceBankBeforeTransaction = accountService.getTotalBalanceOfBank();

        // do transfer from customer test1 to customer test2
        // this test will only succeed if tests in CustomerServiceTest and AccountServiceTest have run (aka test1 and test2 are, with account, present in the database)



        // total balance of bank after transaction
        double totalBankBalanceAfterTransaction = accountService.getTotalBalanceOfBank();

        assertEquals(totalBalanceBankBeforeTransaction,totalBankBalanceAfterTransaction, 0.000001);

        // OR check before and after balance of test1 account and test2 account (instead of the balance of the whole bank)




    }

    // testing deleting transactions, accounts and customers (begin testing next time, without test1 and test2 information present)
}