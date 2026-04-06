package com.finance.Finance_dashboard.controller;

import com.finance.Finance_dashboard.model.FinancialRecord;
import com.finance.Finance_dashboard.model.Role;
import com.finance.Finance_dashboard.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordController {

    @Autowired
    private RecordService service;

    @PostMapping
    public FinancialRecord create(@RequestBody FinancialRecord record,
                                  @RequestParam Role role) {
        return service.create(record, role);
    }

    @GetMapping
    public List<FinancialRecord> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,
                       @RequestParam Role role) {
        service.delete(id, role);
    }
}
