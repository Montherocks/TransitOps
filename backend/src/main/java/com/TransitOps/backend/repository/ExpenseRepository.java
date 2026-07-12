package com.TransitOps.backend.repository;

import com.TransitOps.backend.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExpenseRepository extends JpaRepository<Expense,Long>, JpaSpecificationExecutor<Expense> {
}
