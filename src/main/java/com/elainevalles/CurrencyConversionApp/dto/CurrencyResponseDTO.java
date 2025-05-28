package com.elainevalles.CurrencyConversionApp.dto;

public class CurrencyResponseDTO {
    private final String baseCurrency;
    private final String targetCurrency;
    private final double exchangeRate;
    private final double amountAmount;
    private final double convertedAmount;

    public CurrencyResponseDTO(String baseCurrency, String targetCurrency, double exchangeRate, double amountAmount, double convertedAmount) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.exchangeRate = exchangeRate;
        this.amountAmount = amountAmount;
        this.convertedAmount = convertedAmount;
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

    public double getAmountAmount() {
        return amountAmount;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }
}
