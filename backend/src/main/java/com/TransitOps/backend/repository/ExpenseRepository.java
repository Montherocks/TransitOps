package com.TransitOps.backend.repository;

import com.TransitOps.backend.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense,Long>, JpaSpecificationExecutor<Expense> {
    Page<Expense> findByVehicleId(Long vehicleId, Pageable pageable);
}
