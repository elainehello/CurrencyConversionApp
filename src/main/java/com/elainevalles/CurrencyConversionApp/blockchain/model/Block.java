package com.elainevalles.CurrencyConversionApp.blockchain.model;

import com.elainevalles.CurrencyConversionApp.blockchain.Transaction;
import com.elainevalles.CurrencyConversionApp.blockchain.util.HashUtil;

import java.util.List;

public class Block {
    private String hash;
    private String previousHash;
    private long timeStamp;
    private List<Transaction> transactionList;
    private int nonce;

    public Block(String previousHash, List<Transaction> transactions) {
        this.previousHash = previousHash;
        this.timeStamp = System.currentTimeMillis();
        this.transactionList = transactions;
        this.hash = calculateHash();
        this.nonce = 0;
    }

    private String calculateHash() {
        String data = previousHash + timeStamp + transactionList.toString() + nonce;
        return HashUtil.sha256(data);
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }
}
