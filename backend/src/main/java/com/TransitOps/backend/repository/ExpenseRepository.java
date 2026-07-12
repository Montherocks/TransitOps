package com.TransitOps.backend.repository;

import com.TransitOps.backend.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense,Long>, JpaSpecificationExecutor<Expense> {
    Page<Expense> findByVehicleId(Long vehicleId, Pageable pageable);

    @Query("""
    SELECT COALESCE(SUM(e.amount),0)
    FROM Expense e
    """)
    BigDecimal getTotalExpenseAmount();

    @Query("""
    SELECT COUNT(e)
    FROM Expense e
    """)
    Long getTotalExpenses();
}
