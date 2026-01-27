package com.wikicode96.payment.application.usecase;

public class RetryPaymentUseCase {
    private final PaymentRepository repository;
    private final PaymentCalculator calculator;

    public RetryPaymentUseCase(PaymentRepository repository, PaymentCalculator calculator) {
        this.repository = repository;
        this.calculator = calculator;
    }

    public PaymentResult execute(RetryPaymentCommand command) {
        Payment payment = repository.findById(command.paymentId());
        if(payment == null) throw new PaymentNotFoundException();
        if(!payment.isFailed()) throw new PaymentNotFailedException();
        Payment newPayment = calculator.process(payment.getUserId(), payment.getAmount());
        repository.save(newPayment);
        return new PaymentResult(newPayment.getId(), newPayment.getAmount());
    }
}
