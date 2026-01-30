package com.wikicode96.payment.domain.exception;

public class UserNotActiveException extends RuntimeException {

    public UserNotActiveException(String userId) {
        super("User is not active: " + userId);
    }
}
