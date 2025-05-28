package com.elainevalles.CurrencyConversionApp.blockhain;

import com.elainevalles.CurrencyConversionApp.blockhain.util.HashUtil;

import java.math.BigDecimal;
import java.time.Instant;

public class Transaction {
    private String sender;
    private String recipient;
    private BigDecimal amount;
    private String currency;
    private long timeStamp;
    private String transactionId;

    public Transaction(String sender, String recipient, BigDecimal amount, String currency) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.currency = currency;
    }

    public Transaction(String sender, String recipient, BigDecimal amount, String currency, long timeStamp, String transactionId) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.currency = currency;
        this.timeStamp = Instant.now().toEpochMilli();
        this.transactionId = generateTrasactionId();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    private String generateTransactionId() {
        String input = sender +
                recipient +
                amount.toPlainString() +
                currency +
                timeStamp;
        return HashUtil.sha256(input);
    }

}
