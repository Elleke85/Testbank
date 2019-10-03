package com.example.test.model.dao;

import com.example.test.model.Account;
import com.example.test.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    List<Transaction> findTransactionsByFromAccountId(int accountId);
    List<Transaction> findTransactionsByToAccountId(int accountId) ;

}
