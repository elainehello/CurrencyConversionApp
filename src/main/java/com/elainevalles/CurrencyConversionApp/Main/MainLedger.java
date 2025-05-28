package com.elainevalles.CurrencyConversionApp.Main;

import com.elainevalles.CurrencyConversionApp.blockchain.model.TransactionRequest;
import com.elainevalles.CurrencyConversionApp.service.impl.CurrencyServiceImpl;
import com.elainevalles.CurrencyConversionApp.service.impl.LedgerServiceImpl;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class MainLedger {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        CurrencyServiceImpl currencyService = new CurrencyServiceImpl(restTemplate);
        LedgerServiceImpl ledgerService = new LedgerServiceImpl(currencyService);

        // Add sample transactions
        ledgerService.addTransaction(new TransactionRequest("Alice", "Bob", 100.0, "USD"));
        ledgerService.addTransaction(new TransactionRequest("Bob", "Charlie", 50.0, "USD"));
        ledgerService.addTransaction(new TransactionRequest("Charlie", "Alice", 20.0, "USD"));

        // Print full ledger
        System.out.println("Full Ledger:");
        ledgerService.getFullLedger().forEach(System.out::println);

        // Check balance
        System.out.println("\nBalances:");
        System.out.println("Alice: " + ledgerService.getBalance("Alice"));
        System.out.println("Bob: " + ledgerService.getBalance("Bob"));
        System.out.println("Charlie: " + ledgerService.getBalance("Charlie"));

        // Check ledger validity
        System.out.println("\nIs Ledger Valid? " + ledgerService.isLedgerValid());

        // Test currency conversion
        BigDecimal converted = ledgerService.convertCurrency("USD", "EUR", BigDecimal.valueOf(100));
        System.out.println("\n100 USD to EUR: " + converted);
    }
}
