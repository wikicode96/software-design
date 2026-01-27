package com.wikicode96.payment.application.usecase;

public class NotifyPaymentUseCase {
    private final PaymentRepository repository;
    private final PaymentEventPublisher publisher;

    public NotifyPaymentUseCase(PaymentRepository repository, PaymentEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public void execute(NotifyPaymentCommand command) {
        Payment payment = repository.findById(command.paymentId());
        if(payment == null) throw new PaymentNotFoundException();
        publisher.publishPaymentProcessed(payment);
    }
}
