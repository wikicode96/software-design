package com.wikicode96.payment.domain.model;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class User {
    private final String id;
    private final boolean active;
    private final BigDecimal dailyLimit;

    public User(String id, boolean active, BigDecimal dailyLimit) {
        if (dailyLimit == null || dailyLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Daily limit must be positive");
        }
        this.id = id;
        this.active = active;
        this.dailyLimit = dailyLimit;
    }
}
