    package com.elainevalles.CurrencyConversionApp.service.impl;

    import com.elainevalles.CurrencyConversionApp.model.Currency;
    import com.elainevalles.CurrencyConversionApp.model.ExchangeRateResponse;
    import com.elainevalles.CurrencyConversionApp.service.ICurrencyService;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Map;

    @Service
    public class CurrencyServiceImpl implements ICurrencyService {

        private final RestTemplate restTemplate;

        public CurrencyServiceImpl(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        @Override
        public List<Currency> getExchangeRate(String baseCurrency) {
            String url = "https://api.frankfurter.dev/v1/latest" + "?base=" + baseCurrency;
            ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

            List<Currency> currencyList = new ArrayList<>();
            if (response != null) {
                for (Map.Entry<String, Double> entry : response.getRates().entrySet()) {
                    currencyList.add(new Currency(entry.getKey(), entry.getValue()));
                }
            }
            return currencyList;
        }

        public double convertCurrency(String baseCurrency, String targetCurrency, double amount) {
            String url = "https://api.frankfurter.app/latest?amount=" + amount + "&from=" + baseCurrency + "&to=" + targetCurrency;
            ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

            Double rate = response.getRates().get(targetCurrency);
            if (rate == null) {
                throw new IllegalArgumentException("Invalid target currency: " + targetCurrency);
            }
            return rate;
        }

    }
