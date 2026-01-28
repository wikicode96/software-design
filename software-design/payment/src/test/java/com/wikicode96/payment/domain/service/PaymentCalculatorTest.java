package com.wikicode96.payment.domain.service;

import com.wikicode96.payment.domain.exception.PaymentLimitExceededException;
import com.wikicode96.payment.domain.exception.UserNotActiveException;
import com.wikicode96.payment.domain.model.Payment;
import com.wikicode96.payment.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentCalculatorTest {

    private PaymentCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new PaymentCalculator();
    }

    @Test
    void shouldProcessPaymentWhenUserIsActiveAndAmountWithinLimit() {
        User user = new User("user-1", true, new BigDecimal("100.00"));

        Payment payment = calculator.process(user, new BigDecimal("50.00"));

        assertEquals("user-1", payment.getUserId());
        assertEquals(new BigDecimal("50.00"), payment.getAmount());
    }

    @Test
    void shouldThrowExceptionWhenUserIsNotActive() {
        User user = new User("user-1", false, new BigDecimal("100.00"));

        assertThrows(
                UserNotActiveException.class,
                () -> calculator.process(user, new BigDecimal("10.00"))
        );
    }

    @Test
    void shouldThrowExceptionWhenAmountExceedsDailyLimit() {
        User user = new User("user-1", true, new BigDecimal("100.00"));

        assertThrows(
                PaymentLimitExceededException.class,
                () -> calculator.process(user, new BigDecimal("150.00"))
        );
    }
}
