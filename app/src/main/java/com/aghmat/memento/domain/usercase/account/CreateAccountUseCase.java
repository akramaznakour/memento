package com.aghmat.memento.domain.usercase.account;

import com.aghmat.memento.data.repository.AccountRepository;

import java.time.LocalDate;

import io.reactivex.Completable;

public class CreateAccountUseCase {
    private final AccountRepository accountRepository;

    public CreateAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Completable createAccount(String year, String month, String day, String lifeExpectancy) {
        LocalDate birthDate = LocalDate.of(Integer.parseInt(year),
                Integer.parseInt(month),
                Integer.parseInt(day));

        return accountRepository.createAccount(birthDate, Integer.parseInt(lifeExpectancy));
    }
}
