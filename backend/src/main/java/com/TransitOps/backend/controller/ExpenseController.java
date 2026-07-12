package com.TransitOps.backend.controller;

import com.TransitOps.backend.dto.*;
import com.TransitOps.backend.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ApiResponse<ExpenseResponseDTO>> createExpense(@Valid @RequestBody CreateExpenseRequestDTO request){

        ExpenseResponseDTO response = expenseService.createExpense(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ExpenseResponseDTO>builder()
                        .success(true)
                        .message("Expense created successfully.")
                        .data(response)
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponseDTO>> getExpenseById(@PathVariable Long id){

        return ResponseEntity.ok(
                ApiResponse.<ExpenseResponseDTO>builder()
                        .success(true)
                        .message("Expense fetched successfully.")
                        .data(expenseService.getExpenseById(id))
                        .build()
        );

    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ExpenseResponseDTO>>> getAllExpenses(

            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "10")
            int size,
            @RequestParam(defaultValue = "id")
            String sortBy,
            @RequestParam(defaultValue = "asc")
            String direction
    ){

        return ResponseEntity.ok(
                ApiResponse.<Page<ExpenseResponseDTO>>builder()
                        .success(true)
                        .message("Expenses fetched successfully.")
                        .data(expenseService.getAllExpenses(page, size, sortBy, direction))
                        .build()
        );

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponseDTO>> updateExpense(
            @PathVariable Long id,
            @Valid
            @RequestBody
            UpdateExpenseRequestDTO request
    ){

        return ResponseEntity.ok(
                ApiResponse.<ExpenseResponseDTO>builder()
                        .success(true)
                        .message("Expense updated successfully.")
                        .data(expenseService.updateExpense(id,request))
                        .build()
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteExpense(@PathVariable Long id){

        expenseService.deleteExpense(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Expense deleted successfully.")
                        .build()
        );

    }

    @GetMapping("/statistics")
    public ResponseEntity<ApiResponse<ExpenseStatisticsDTO>> statistics(){

        return ResponseEntity.ok(
                ApiResponse.<ExpenseStatisticsDTO>builder()
                        .success(true)
                        .message("Expense statistics fetched successfully.")
                        .data(expenseService.getStatistics())
                        .build()
        );

    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<ApiResponse<Page<ExpenseResponseDTO>>> getExpensesByVehicle(

            @PathVariable Long vehicleId,

            @RequestParam(defaultValue = "0")
            int page,
            @RequestParam(defaultValue = "10")
            int size

    ){

        return ResponseEntity.ok(

                ApiResponse.<Page<ExpenseResponseDTO>>builder()
                        .success(true)
                        .message("Vehicle expenses fetched successfully.")
                        .data(expenseService.getExpensesByVehicle(vehicleId, page, size))
                        .build()

        );

    }

}