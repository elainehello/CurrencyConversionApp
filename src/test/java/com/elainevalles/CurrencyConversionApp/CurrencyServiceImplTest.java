package com.elainevalles.CurrencyConversionApp;

import com.elainevalles.CurrencyConversionApp.model.Currency;
import com.elainevalles.CurrencyConversionApp.model.ExchangeRateResponse;
import com.elainevalles.CurrencyConversionApp.service.impl.CurrencyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CurrencyServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    private CurrencyServiceImpl currencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        currencyService = new CurrencyServiceImpl(restTemplate);
    }

    @Test
    void testGetExchangeRateReturnsCurrencyList() {
        String baseCurrency = "USD";
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 0.9);
        rates.put("GBP", 0.8);

        ExchangeRateResponse mockResponse = new ExchangeRateResponse();
        mockResponse.setRates(rates);

        when(restTemplate.getForObject(anyString(), eq(ExchangeRateResponse.class))).thenReturn(mockResponse);

        List<Currency> result = currencyService.getExchangeRate(baseCurrency);

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(c -> c.getCurrency().equals("EUR") && c.getRate() == 0.9));
        assertTrue(result.stream().anyMatch(c -> c.getCurrency().equals("GBP") && c.getRate() == 0.8));


        verify(restTemplate, times(1)).getForObject(contains(baseCurrency), eq(ExchangeRateResponse.class));
    }

    @Test
    void testGetExchangeRateHandlesNullResponse() {
        when(restTemplate.getForObject(anyString(), eq(ExchangeRateResponse.class))).thenReturn(null);

        List<Currency> result = currencyService.getExchangeRate("USD");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testConvertCurrencyReturnsCorrectRate() {
        String baseCurrency = "USD";
        String targetCurrency = "EUR";
        double amount = 100.0;

        Map<String, Double> rates = new HashMap<>();
        rates.put(targetCurrency, 0.9);

        ExchangeRateResponse mockResponse = new ExchangeRateResponse();
        mockResponse.setRates(rates);

        when(restTemplate.getForObject(anyString(), eq(ExchangeRateResponse.class))).thenReturn(mockResponse);

        double result = currencyService.convertCurrency(baseCurrency, targetCurrency, amount);

        assertEquals(0.9, result);

        verify(restTemplate, times(1)).getForObject(contains("amount=" + amount), eq(ExchangeRateResponse.class));
    }

    @Test
    void testConvertCurrencyThrowsExceptionForInvalidTarget() {
        String baseCurrency = "USD";
        String targetCurrency = "INVALID";
        double amount = 100.0;

        ExchangeRateResponse mockResponse = new ExchangeRateResponse();
        mockResponse.setRates(new HashMap<>()); // no rates returned

        when(restTemplate.getForObject(anyString(), eq(ExchangeRateResponse.class))).thenReturn(mockResponse);

        assertThrows(IllegalArgumentException.class, () ->
                currencyService.convertCurrency(baseCurrency, targetCurrency, amount));
    }
}
