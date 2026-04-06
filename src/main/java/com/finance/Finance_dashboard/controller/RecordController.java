package com.finance.Finance_dashboard.controller;

import com.finance.Finance_dashboard.model.FinancialRecord;
import com.finance.Finance_dashboard.model.Role;
import com.finance.Finance_dashboard.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/filter")
    public List<FinancialRecord> filter(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        return service.filter(
                type,
                category,
                startDate != null ? LocalDate.parse(startDate) : null,
                endDate != null ? LocalDate.parse(endDate) : null
        );
    }

    @GetMapping("/summary")
    public Map<String, Object> summary() {
        return service.getSummary();
    }
}
