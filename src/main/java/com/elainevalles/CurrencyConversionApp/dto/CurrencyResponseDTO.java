package com.elainevalles.CurrencyConversionApp.dto;

public class CurrencyResponseDTO {
    private final String baseCurrency;
    private final String targetCurrency;
    private final double exchangeRate;
    private final double originalAmount;
    private final double convertedAmount;

    public CurrencyResponseDTO(String baseCurrency, String targetCurrency, double exchangeRate, double originalAmount, double convertedAmount) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = exchangeRate;
        this.originalAmount = originalAmount;
        this.convertedAmount = convertedAmount;
    }

    public CurrencyResponseDTO(String baseCurrency, String targetCurrency, double originalAmount) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.originalAmount = originalAmount;
        this.exchangeRate = 0.0;      // default value
        this.convertedAmount = 0.0;   // default value
    }

    public String getBaseCurrency() {

        return baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }
}
