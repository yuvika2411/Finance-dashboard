package com.finance.Finance_dashboard.repository;

import com.finance.Finance_dashboard.model.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<FinancialRecord, Long> {}