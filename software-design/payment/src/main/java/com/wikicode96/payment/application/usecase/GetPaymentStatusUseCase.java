package com.wikicode96.payment.application.usecase;

public class GetPaymentStatusUseCase {

    private final PaymentRepository repository;

    public GetPaymentStatusUseCase(PaymentRepository repository) {
        this.repository = repository;
    }

    public PaymentStatusResult execute(GetPaymentStatusCommand command) {
        Payment payment = repository.findById(command.paymentId());
        if(payment == null) throw new PaymentNotFoundException();
        return new PaymentStatusResult(payment.getId(), payment.getStatus());
    }
}
