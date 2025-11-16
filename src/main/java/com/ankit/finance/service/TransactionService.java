package com.ankit.finance.service;

import com.ankit.finance.model.Transaction;
import com.ankit.finance.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository repo;
    public TransactionService(TransactionRepository repo) { this.repo = repo; }

    public Transaction save(Transaction t) { return repo.save(t); }
    public List<Transaction> findAll() { return repo.findAll(); }
    public void delete(Long id) { repo.deleteById(id); }
    public List<Transaction> findBetween(LocalDate s, LocalDate e) { return repo.findByDateBetweenOrderByDateDesc(s,e); }
}
