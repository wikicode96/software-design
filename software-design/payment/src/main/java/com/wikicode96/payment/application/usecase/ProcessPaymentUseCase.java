package com.wikicode96.payment.application.usecase;

import org.springframework.stereotype.Service;

@Service
public class ProcessPaymentUseCase {

    private final PaymentRepository repository;
    private final PaymentCalculator calculator;

    public ProcessPaymentUseCase(PaymentRepository repository,
                                 PaymentCalculator calculator) {
        this.repository = repository;
        this.calculator = calculator;
    }

    public PaymentResult execute(ProcessPaymentCommand command) {
        // 1️⃣ Map Command → Domain (VOs si quieres)
        String userId = command.userId();
        var amount = command.amount();

        // 2️⃣ Lógica de dominio
        Payment payment = calculator.process(userId, amount);

        // 3️⃣ Persistir resultado usando port
        repository.save(payment);

        // 4️⃣ Map Domain → Result
        return new PaymentResult(payment.getId(), payment.getAmount());
    }
}
