package com.elainevalles.CurrencyConversionApp.service;

import com.elainevalles.CurrencyConversionApp.dto.CurrencyRequestDTO;
import com.elainevalles.CurrencyConversionApp.dto.CurrencyResponseDTO;
import com.elainevalles.CurrencyConversionApp.model.Currency;
import java.util.List;

public interface ICurrencyService {
    List<Currency> getExchangeRate(String baseCurrency);
    double convertCurrency(String baseCurrency, String targetCurrency, double amount);
    CurrencyResponseDTO convert(CurrencyRequestDTO requestDTO);
}
