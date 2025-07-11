package com.elainevalles.CurrencyConversionApp.service;

import com.elainevalles.CurrencyConversionApp.blockchain.model.Block;
import com.elainevalles.CurrencyConversionApp.blockchain.model.TransactionRequest;

import java.math.BigDecimal;
import java.util.List;

public interface ILedgerService {
    void addTransaction(TransactionRequest request);
    List<Block> getFullLedger();
    boolean isLedgerValid();
    BigDecimal getBalance(String accountId);
    BigDecimal convertCurrency(String fromCurrency, String toCurrency, BigDecimal amount);

}
