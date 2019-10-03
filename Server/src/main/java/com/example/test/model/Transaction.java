package com.example.test.model;

import com.example.test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Transaction {


    @Id
    @GeneratedValue
    private int transferNr;
    private String date;
    private String time;
    private String description;
    private int fromAccountId;
    private String fromAccountName;
    private int toAccountId;
    private String toAccountName;
    private double amount;
    private String typeOfTransfer;


    public Transaction() {
        this("",0,0,0);
    }

    public Transaction(String description, int fromAccountId, int toAccount, double amount) {
        this.description = description;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccount;
        this.amount = amount;
    }

    public int getTransferNr() {
        return transferNr;
    }
    public void setTransferNr(int transferNr) {
        this.transferNr = transferNr;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getFromAccountId() {
        return fromAccountId;
    }
    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }
    public int getToAccountId() {
        return toAccountId;
    }
    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getTypeOfTransfer() {
        return typeOfTransfer;
    }
    public void setTypeOfTransfer(String typeOfTransfer) {
        this.typeOfTransfer = typeOfTransfer;
    }
    public String getFromAccountName() {
        return fromAccountName;
    }
    public void setFromAccountName(String fromAccountName) {
        this.fromAccountName = fromAccountName;
    }
    public String getToAccountName() {
        return toAccountName;
    }
    public void setToAccountName(String toAccountName) {
        this.toAccountName = toAccountName;
    }
}
