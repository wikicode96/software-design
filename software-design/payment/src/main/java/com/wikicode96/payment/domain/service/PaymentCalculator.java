package com.wikicode96.payment.domain.service;

import com.wikicode96.payment.domain.exception.PaymentLimitExceededException;
import com.wikicode96.payment.domain.exception.UserNotActiveException;
import com.wikicode96.payment.domain.model.Payment;
import com.wikicode96.payment.domain.model.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentCalculator {

    public Payment process(User user, BigDecimal amount) {

        if (!user.isActive()) {
            throw new UserNotActiveException(user.getId());
        }

        if (amount.compareTo(user.getDailyLimit()) > 0) {
            throw new PaymentLimitExceededException(amount, user.getDailyLimit());
        }

        return new Payment(user.getId(), amount);
    }
}
