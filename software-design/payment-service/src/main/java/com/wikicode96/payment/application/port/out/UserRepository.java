package com.wikicode96.payment.application.port.out;

import com.wikicode96.payment.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(String id);
}
