package io.github.cajueiro.application.service;


import io.github.cajueiro.domain.Transaction;
import jakarta.transaction.Transactional;

public interface AuthorizationService {

    @Transactional
    String authorizeTransaction(Transaction transaction);
}
