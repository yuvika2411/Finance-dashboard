package com.finance.Finance_dashboard.service;

import com.finance.Finance_dashboard.model.FinancialRecord;
import com.finance.Finance_dashboard.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private RecordRepository repo;

    public Map<String, Double> getSummary() {

        List<FinancialRecord> records = repo.findAll();

        double income = 0;
        double expense = 0;

        for(FinancialRecord r : records){
            if(r.getType().equals("INCOME")){
                income += r.getAmount();
            } else {
                expense += r.getAmount();
            }
        }

        Map<String, Double> map = new HashMap<>();
        map.put("income", income);
        map.put("expense", expense);
        map.put("balance", income - expense);

        return map;
    }
}
