package com.finance.Finance_dashboard.repository;

import com.finance.Finance_dashboard.model.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RecordRepository extends JpaRepository<FinancialRecord, Long> {
    List<FinancialRecord> findByType(String type);
    List<FinancialRecord> findByCategory(String category);
    List<FinancialRecord> findByDateBetween(LocalDate start, LocalDate end);
}