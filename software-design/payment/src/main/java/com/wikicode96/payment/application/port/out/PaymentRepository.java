package com.wikicode96.payment.application.port.out;

import com.wikicode96.payment.domain.model.Payment;

public interface PaymentRepository {
    void save(Payment payment);
    Payment findById(String paymentId);
}
