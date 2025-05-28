package com.elainevalles.CurrencyConversionApp;

import com.elainevalles.CurrencyConversionApp.blockchain.model.Block;
import com.elainevalles.CurrencyConversionApp.blockchain.model.TransactionRequest;
import com.elainevalles.CurrencyConversionApp.service.ICurrencyService;
import com.elainevalles.CurrencyConversionApp.service.impl.LedgerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LedgerServiceImplTest {

    private ICurrencyService currencyService;
    private LedgerServiceImpl ledgerService;

    @BeforeEach
    void setUp() {
        currencyService = mock(ICurrencyService.class);
        ledgerService = new LedgerServiceImpl(currencyService);
    }

    @Test
    void testAddTransactionAndGetFullLedger() {
        TransactionRequest request = new TransactionRequest("Alice", "Bob", 100.0, "USD");
        ledgerService.addTransaction(request);


        List<Block> ledger = ledgerService.getFullLedger();
        assertEquals(1, ledger.size());
        assertEquals("Alice", ledger.get(0).getTransactionList().get(0).getSender());
        assertEquals("Bob", ledger.get(0).getTransactionList().get(0).getRecipient());
    }

    @Test
    void testIsLedgerValidTrue() {
        ledgerService.addTransaction(new TransactionRequest("A", "B", 50.0, "USD"));
        ledgerService.addTransaction(new TransactionRequest("B", "C", 20.0, "USD"));
        assertTrue(ledgerService.isLedgerValid());
    }

    @Test
    void testIsLedgerValidFalseWithEmptyLedger() {
        assertFalse(ledgerService.isLedgerValid());
    }

    @Test
    void testGetBalance() {
        ledgerService.addTransaction(new TransactionRequest("Alice", "Bob", 100.0, "USD"));
        ledgerService.addTransaction(new TransactionRequest("Bob", "Charlie", 30.0, "USD"));

        BigDecimal aliceBalance = ledgerService.getBalance("Alice");
        BigDecimal bobBalance = ledgerService.getBalance("Bob");
        BigDecimal charlieBalance = ledgerService.getBalance("Charlie");

        assertEquals(BigDecimal.valueOf(-100.0), aliceBalance);
        assertEquals(BigDecimal.valueOf(70.0), bobBalance);
        assertEquals(BigDecimal.valueOf(30.0), charlieBalance);
    }

    @Test
    void testConvertCurrency() {
        when(currencyService.convertCurrency("USD", "EUR", 100.0)).thenReturn(90.0);
        BigDecimal result = ledgerService.convertCurrency("USD", "EUR", BigDecimal.valueOf(100));

        assertEquals(BigDecimal.valueOf(90.0), result);
        verify(currencyService, times(1)).convertCurrency("USD", "EUR", 100.0);
    }

    @Test
    void testConvertCurrencyWithInvalidAmount() {
        assertThrows(IllegalArgumentException.class, () ->
                ledgerService.convertCurrency("USD", "EUR", BigDecimal.ZERO));
    }
}
