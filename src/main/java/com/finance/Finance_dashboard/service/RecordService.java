package com.finance.Finance_dashboard.service;

import com.finance.Finance_dashboard.model.FinancialRecord;
import com.finance.Finance_dashboard.model.Role;
import com.finance.Finance_dashboard.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;   
import java.util.List;
import java.util.Map;

@Service
public class RecordService {

    @Autowired
    private RecordRepository repo;

    public FinancialRecord create(FinancialRecord record, Role role) {

        if (role != Role.ADMIN) {
            throw new RuntimeException("Only admin can create records");
        }

        if (record.getAmount() <= 0) {
            throw new RuntimeException("Amount must be positive");
        }

        return repo.save(record);
    }

    public List<FinancialRecord> getAll() {
        return repo.findAll();
    }

    public void delete(Long id, Role role) {

        if (role != Role.ADMIN) {
            throw new RuntimeException("Only admin can delete");
        }

        repo.deleteById(id);
    }

    public List<FinancialRecord> filter(String type, String category, LocalDate start, LocalDate end) {

        if (type != null && !type.isEmpty()) {
            return repo.findByType(type);
        }

        if (category != null && !category.isEmpty()) {
            return repo.findByCategory(category);
        }

        if (start != null && end != null) {
            return repo.findByDateBetween(start, end);
        }

        return repo.findAll();
    }

    public Map<String, Object> getSummary() {

        List<FinancialRecord> records = repo.findAll();

        double income = records.stream()
                .filter(r -> "INCOME".equalsIgnoreCase(r.getType()))
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        double expense = records.stream()
                .filter(r -> "EXPENSE".equalsIgnoreCase(r.getType()))
                .mapToDouble(FinancialRecord::getAmount)
                .sum();

        Map<String, Object> map = new HashMap<>();
        map.put("totalIncome", income);
        map.put("totalExpense", expense);
        map.put("balance", income - expense);

        return map;
    }
}