package com.savings_app.Savings.Aplication.controller;

import com.savings_app.Savings.Aplication.dto.FinancialDataDto;
import com.savings_app.Savings.Aplication.service.FinancialDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/users/{userId}/financial-data")
public class FinancialDataController {

    private final FinancialDataService financialDataService;

    // Get Financial Data
    @GetMapping
    public ResponseEntity<FinancialDataDto> getFinancialDataByUserId(@PathVariable("userId") Long userId) {
        FinancialDataDto financialData = financialDataService.getFinancialDataByUserId(userId);
        return ResponseEntity.ok(financialData);
    }

    // Create Financial Data
    @PostMapping
    public ResponseEntity<FinancialDataDto> saveFinancialData(
            @PathVariable("userId") Long userId,
            @RequestBody FinancialDataDto financialDataDto) {

        FinancialDataDto savedFinancialData = financialDataService.saveFinancialData(userId, financialDataDto);
        return new ResponseEntity<>(savedFinancialData, HttpStatus.CREATED);
    }

    // Update Financial Data
    @PutMapping
    public ResponseEntity<FinancialDataDto> updateFinancialData(
            @PathVariable("userId") Long userId,
            @RequestBody FinancialDataDto financialDataDto) {

        FinancialDataDto updatedFinancialData = financialDataService.updateFinancialData(userId, financialDataDto);
        return ResponseEntity.ok(updatedFinancialData);
    }

    // Delete Financial Data
    @DeleteMapping
    public ResponseEntity<String> deleteFinancialData(@PathVariable("userId") Long userId) {
        financialDataService.deleteFinancialData(userId);
        return ResponseEntity.ok("Financial data deleted successfully");
    }
}
