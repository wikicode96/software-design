package com.wikicode96.payment.application.command;

import java.math.BigDecimal;

public record ProcessPaymentCommand(String userId, BigDecimal amount) {}
