package io.github.cajueiro.application.controller;

import io.github.cajueiro.application.service.AuthorizationService;
import io.github.cajueiro.domain.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public final class TransactionController {

    private final AuthorizationService authorizationService;

    public TransactionController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping
    public ResponseEntity<?> authorizeTransaction(@RequestBody Transaction transaction) {
        String result = authorizationService.authorizeTransaction(transaction);
        return ResponseEntity.ok().body("{\"code\": \"" + result + "\"}");
    }
}

