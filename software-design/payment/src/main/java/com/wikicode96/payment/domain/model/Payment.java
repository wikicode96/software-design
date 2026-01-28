package com.wikicode96.payment.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Payment {

    private final String id;
    private final String userId;
    private final BigDecimal amount;

    public Payment(String userId, BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.amount = amount;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public BigDecimal getAmount() { return amount; }
}
