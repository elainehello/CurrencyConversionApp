package com.elainevalles.CurrencyConversionApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ledger/")
public class ledgerController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
