package com.wikicode96.payment.application.usecase;

public class CancelPaymentUseCase {
    private final PaymentRepository repository;

    public CancelPaymentUseCase(PaymentRepository repository) {
        this.repository = repository;
    }

    public void execute(CancelPaymentCommand command) { // TDOD
        Payment payment = repository.findById(command.paymentId());
        if(payment == null) throw new PaymentNotFoundException();
        if(!payment.isCancellable()) throw new PaymentCannotBeCancelledException();
        payment.cancel(); // método del dominio
        repository.save(payment);
    }
}
