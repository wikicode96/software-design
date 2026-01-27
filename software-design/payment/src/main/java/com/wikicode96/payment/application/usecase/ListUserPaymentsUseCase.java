package com.wikicode96.payment.application.usecase;

public class ListUserPaymentsUseCase {
    private final PaymentRepository repository;

    public ListUserPaymentsUseCase(PaymentRepository repository) {
        this.repository = repository;
    }

    public List<UserPaymentDto> execute(ListUserPaymentsCommand command) {
        return repository.findByUserId(command.userId())
                .stream()
                .map(p -> new UserPaymentDto(p.getId(), p.getAmount(), p.getStatus()))
                .toList();
    }
}
