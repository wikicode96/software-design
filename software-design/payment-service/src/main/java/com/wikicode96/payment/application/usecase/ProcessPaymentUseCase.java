package com.wikicode96.payment.application.usecase;

import com.wikicode96.payment.application.command.ProcessPaymentCommand;
import com.wikicode96.payment.application.mapper.PaymentMapper;
import com.wikicode96.payment.application.port.out.PaymentRepository;
import com.wikicode96.payment.application.port.out.UserRepository;
import com.wikicode96.payment.application.result.PaymentResult;
import com.wikicode96.payment.domain.exception.UserNotFoundException;
import com.wikicode96.payment.domain.model.Payment;
import com.wikicode96.payment.domain.model.User;
import com.wikicode96.payment.domain.service.PaymentCalculator;
import org.springframework.stereotype.Service;

@Service
public class ProcessPaymentUseCase {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final PaymentCalculator calculator;
    private final PaymentMapper mapper;

    public ProcessPaymentUseCase(PaymentRepository paymentRepository,
                                 UserRepository userRepository,
                                 PaymentCalculator calculator,
                                 PaymentMapper mapper) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.calculator = calculator;
        this.mapper = mapper;
    }

    public PaymentResult execute(ProcessPaymentCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new UserNotFoundException(command.userId()));
        Payment payment = calculator.process(user, command.amount());
        paymentRepository.save(payment);
        return mapper.toPaymentResult(payment);
    }
}

