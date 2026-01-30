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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessPaymentUseCaseTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PaymentMapper mapper;

    private PaymentCalculator calculator;

    private ProcessPaymentUseCase useCase;

    @BeforeEach
    void setUp() {
        calculator = new PaymentCalculator();
        useCase = new ProcessPaymentUseCase(
                paymentRepository,
                userRepository,
                calculator,
                mapper
        );
    }

    @Test
    void shouldProcessPaymentSuccessfully() {
        ProcessPaymentCommand command =
                new ProcessPaymentCommand("user-1", new BigDecimal("50.00"));

        User user = new User("user-1", true, new BigDecimal("100.00"));
        Payment payment = new Payment("user-1", new BigDecimal("50.00"));
        PaymentResult result = new PaymentResult("payment-1", new BigDecimal("50.00"));

        when(userRepository.findById("user-1")).thenReturn(Optional.of(user));
        when(mapper.toPaymentResult(any(Payment.class))).thenReturn(result);

        PaymentResult response = useCase.execute(command);

        assertEquals(result, response);
        verify(paymentRepository).save(any(Payment.class));
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {
        ProcessPaymentCommand command =
                new ProcessPaymentCommand("user-404", new BigDecimal("50.00"));

        when(userRepository.findById("user-404")).thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class,
                () -> useCase.execute(command)
        );

        verify(paymentRepository, never()).save(any());
    }

    @Test
    void userNotFoundExceptionShouldContainUserId() {
        UserNotFoundException ex = new UserNotFoundException("user-1");
        assertTrue(ex.getMessage().contains("user-1"));
    }
}
