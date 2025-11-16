package com.ankit.finance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @Column(length = 2000)
    private String note;

    @NotNull
    private Double amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TxnType type; // INCOME or EXPENSE

    @NotNull
    private LocalDate date;

    private String category;

    public Transaction() {}

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public TxnType getType() { return type; }
    public void setType(TxnType type) { this.type = type; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
