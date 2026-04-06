package com.finance.Finance_dashboard.service;

import com.finance.Finance_dashboard.model.FinancialRecord;
import com.finance.Finance_dashboard.model.Role;
import com.finance.Finance_dashboard.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordRepository repo;

    public FinancialRecord create(FinancialRecord record, Role role) {

        if(role != Role.ADMIN){
            throw new RuntimeException("Only admin can create records");
        }

        if(record.getAmount() <= 0){
            throw new RuntimeException("Amount must be positive");
        }

        return repo.save(record);
    }

    public List<FinancialRecord> getAll() {
        return repo.findAll();
    }

    public void delete(Long id, Role role) {

        if(role != Role.ADMIN){
            throw new RuntimeException("Only admin can delete");
        }

        repo.deleteById(id);
    }
}
