package com.wikicode96.payment.domain.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateUserWhenDailyLimitIsValid() {
        User user = new User(
                "user-1",
                true,
                new BigDecimal("100.00")
        );

        assertEquals("user-1", user.getId());
        assertTrue(user.isActive());
        assertEquals(new BigDecimal("100.00"), user.getDailyLimit());
    }

    @Test
    void shouldAllowDailyLimitZero() {
        User user = new User(
                "user-1",
                true,
                BigDecimal.ZERO
        );

        assertEquals(BigDecimal.ZERO, user.getDailyLimit());
    }

    @Test
    void shouldThrowExceptionWhenDailyLimitIsNegative() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(
                        "user-1",
                        true,
                        new BigDecimal("-1.00")
                )
        );

        assertEquals("Daily limit must be positive", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDailyLimitIsNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new User(
                        "user-1",
                        true,
                        null
                )
        );

        assertEquals("Daily limit must be positive", exception.getMessage());
    }
}