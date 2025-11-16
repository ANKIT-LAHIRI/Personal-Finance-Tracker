package com.ankit.finance.dto;

import com.ankit.finance.model.TxnType;
import java.time.LocalDate;

public class TransactionDto {
    public Long id;
    public String title;
    public String note;
    public Double amount;
    public TxnType type;
    public LocalDate date;
    public String category;
}
