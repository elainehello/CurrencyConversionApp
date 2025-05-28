package com.elainevalles.CurrencyConversionApp.model;

public class Currency {
    private String currency;
    private double rate;

    public Currency(String currency, double rate) {
        this.currency = currency;
        this.rate = rate;
    }
    public String getCurrency() {
        return currency;
    }
    public double getRate() {
        return rate;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "currency [currency=" + currency + ", rate=" + rate + "]";
    }
}
