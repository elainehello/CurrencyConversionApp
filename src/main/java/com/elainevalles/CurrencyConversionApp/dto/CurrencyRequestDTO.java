package com.elainevalles.CurrencyConversionApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CurrencyRequestDTO {

    @NotBlank
    private String baseCurrency;
    @NotBlank
    private String targetCurrency;
    @Positive
    private double amount;

    public CurrencyRequestDTO() {
    }

    public CurrencyRequestDTO(String baseCurrency, String targetCurrency, double amount) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.amount = amount;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
