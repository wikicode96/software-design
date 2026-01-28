package com.wikicode96.payment.application.result;

import java.math.BigDecimal;

public record PaymentResult(String paymentId, BigDecimal amount) { }
