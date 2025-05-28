package com.elainevalles.CurrencyConversionApp.controller;

import com.elainevalles.CurrencyConversionApp.dto.CurrencyRequestDTO;
import com.elainevalles.CurrencyConversionApp.dto.CurrencyResponseDTO;
import com.elainevalles.CurrencyConversionApp.model.Currency;
import com.elainevalles.CurrencyConversionApp.service.ICurrencyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/currency/")
public class currencyController {

    private final ICurrencyService currencyService;

    public currencyController(ICurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/convert")
    public ResponseEntity<CurrencyResponseDTO> convertGet(
            @RequestParam String baseCurrency,
            @RequestParam String targetCurrency,
            @RequestParam double amount) {

        CurrencyRequestDTO requestDTO = new CurrencyRequestDTO();
        requestDTO.setBaseCurrency(baseCurrency);
        requestDTO.setTargetCurrency(targetCurrency);
        requestDTO.setAmount(amount);

        CurrencyResponseDTO responseDTO = currencyService.convert(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/convert")
    public ResponseEntity<CurrencyResponseDTO> convert(@Valid @RequestBody CurrencyRequestDTO requestDTO) {
        CurrencyResponseDTO responseDTO = currencyService.convert(requestDTO);
        return ResponseEntity.ok(responseDTO);

    }
}
