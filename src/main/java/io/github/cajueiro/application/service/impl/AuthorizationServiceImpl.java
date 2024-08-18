package io.github.cajueiro.application.service.impl;

import io.github.cajueiro.application.service.AuthorizationService;
import io.github.cajueiro.domain.Account;
import io.github.cajueiro.domain.Transaction;
import io.github.cajueiro.infra.AccountRepository;
import io.github.cajueiro.infra.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AuthorizationServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public String authorizeTransaction(Transaction transaction) {
        Account account = accountRepository.findAccountById(transaction.getAccountId());

        if (account == null) {
            return "07";
        }

        double amount = transaction.getAmount();
        String mcc = transaction.getMcc();

        // Regra para determinar qual saldo utilizar
        if (mcc.equals("5411") || mcc.equals("5412")) {
            if (account.getFoodBalance() >= amount) {
                account.setFoodBalance(account.getFoodBalance() - amount);
                transactionRepository.save(transaction);
                return "00"; // Transação aprovada
            }
        } else if (mcc.equals("5811") || mcc.equals("5812")) {
            if (account.getMealBalance() >= amount) {
                account.setMealBalance(account.getMealBalance() - amount);
                transactionRepository.save(transaction);
                return "00"; // Transação aprovada
            }
        } else {
            if (account.getCashBalance() >= amount) {
                account.setCashBalance(account.getCashBalance() - amount);
                transactionRepository.save(transaction);
                return "00"; // Transação aprovada
            }
        }

        return "51"; // Saldo insuficiente
    }
}

