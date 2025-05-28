package com.elainevalles.CurrencyConversionApp.Main;

import com.elainevalles.CurrencyConversionApp.model.Currency;
import com.elainevalles.CurrencyConversionApp.service.impl.CurrencyServiceImpl;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MainCurrency {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        CurrencyServiceImpl currencyService = new CurrencyServiceImpl(restTemplate);

        // Test getExchangeRate
        List<Currency> rates = currencyService.getExchangeRate("USD");
        System.out.println("Exchange rates for USD:");
        for (Currency currency : rates) {
            System.out.println(currency);
        }

        // Test convertCurrency
        double result = currencyService.convertCurrency("USD", "EUR", 100);
        System.out.println("100 USD to EUR: " + result);

        // Test convertCurrency 2
        double result2 = currencyService.convertCurrency("USD", "GBP", 100);
        System.out.println("100 USD to GBP: " + result2);

        // Test convertCurrency 3
        double result3 = currencyService.convertCurrency("USD", "CAD", 100);
        System.out.println("100 USD to CAD: " + result3);

        // Test convertCurrency 4
        double result4 = currencyService.convertCurrency("USD", "CHF", 100);
        System.out.println("100 USD to CHF: " + result4);
    }
}
