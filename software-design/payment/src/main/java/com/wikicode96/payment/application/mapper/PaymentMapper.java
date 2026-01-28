package com.wikicode96.payment.application.mapper;

import com.wikicode96.payment.application.result.PaymentResult;
import com.wikicode96.payment.domain.model.Payment;

public class PaymentMapper {
    public PaymentResult toPaymentResult(Payment payment) {
        return new PaymentResult(payment.getId(), payment.getAmount());
    }
}
