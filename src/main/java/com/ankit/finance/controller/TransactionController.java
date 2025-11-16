package com.ankit.finance.controller;

import com.ankit.finance.model.Transaction;
import com.ankit.finance.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService service;
    public TransactionController(TransactionService service) { this.service = service; }

    @GetMapping
    public List<Transaction> all(@RequestParam(required = false) String month) {
        if (month != null && month.matches("\\\\d{4}-\\d{2}")) {
            String[] parts = month.split("-");
            int y = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);
            LocalDate start = LocalDate.of(y, m, 1);
            LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
            return service.findBetween(start, end);
        }
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Transaction> create(@Valid @RequestBody Transaction t) {
        if (t.getDate() == null) t.setDate(LocalDate.now());
        return ResponseEntity.ok(service.save(t));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> update(@PathVariable Long id, @Valid @RequestBody Transaction t) {
        t.setId(id);
        return ResponseEntity.ok(service.save(t));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
