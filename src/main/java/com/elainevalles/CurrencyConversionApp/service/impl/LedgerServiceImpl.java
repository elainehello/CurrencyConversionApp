package com.elainevalles.CurrencyConversionApp.service.impl;

import com.elainevalles.CurrencyConversionApp.blockchain.Transaction;
import com.elainevalles.CurrencyConversionApp.blockchain.model.Block;
import com.elainevalles.CurrencyConversionApp.blockchain.model.TransactionRequest;
import com.elainevalles.CurrencyConversionApp.service.ICurrencyService;
import com.elainevalles.CurrencyConversionApp.service.ILedgerService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LedgerServiceImpl implements ILedgerService {

    private final List<Block> blockchain = new ArrayList<>();
    ICurrencyService currencyService;

    public LedgerServiceImpl(ICurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public void addTransaction(TransactionRequest request) {
        String previousHash;

        if (blockchain.isEmpty()) {
            previousHash = "0";
        } else {
            previousHash = blockchain.get(blockchain.size() - 1).getHash();
        }

        Block newBlock = new Block(previousHash,
                List.of(new Transaction(
                        request.getSender(),
                        request.getRecipient(),
                        BigDecimal.valueOf(request.getAmount()),
                        request.getCurrency()
                ))
        );

        blockchain.add(newBlock);
    }

    @Override
    public List<Block> getFullLedger() {
        return blockchain;
    }

    @Override
    public boolean isLedgerValid() {
        if (blockchain.isEmpty()) {
            return false;
        }
        return IntStream.range(1, blockchain.size())
                .allMatch(i-> {
                    Block current = blockchain.get(i);
                    Block previous = blockchain.get(i - 1);
                    String recalculateHash = current.getHash();
                    return current.getHash()
                            .equals(recalculateHash)
                            && current.getPreviousHash()
                            .equals(previous.getHash());
                });
    }

    @Override
    public BigDecimal getBalance(String accountId) {
        BigDecimal balance = BigDecimal.ZERO;

        for (Block block : blockchain) {
            for (Transaction transaction : block.getTransactionList()) {
                if (transaction.getSender().equals(accountId)) {
                    balance = balance.subtract(transaction.getAmount());
                }
                if (transaction.getRecipient().equals(accountId)) {
                    balance = balance.add(transaction.getAmount());
                }
            }
        }

        return balance;
    }


    @Override
    public BigDecimal convertCurrency(String fromCurrency, String toCurrency, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        double convertedAmount = currencyService.convertCurrency(fromCurrency, toCurrency, amount.doubleValue());
        return BigDecimal.valueOf(convertedAmount);
    }

}
