package com.elainevalles.CurrencyConversionApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/currency/")
public class currencyController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
