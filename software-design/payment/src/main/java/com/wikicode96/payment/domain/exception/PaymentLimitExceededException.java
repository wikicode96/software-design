package com.wikicode96.payment.domain.exception;

import java.math.BigDecimal;

public class PaymentLimitExceededException extends RuntimeException {

    public PaymentLimitExceededException(BigDecimal amount, BigDecimal limit) {
        super("Payment amount " + amount + " exceeds daily limit " + limit);
    }
}
