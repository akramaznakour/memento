package com.aghmat.memento.domain.usercase.account;

import com.aghmat.memento.data.repository.AccountRepository;
import com.aghmat.memento.domain.model.Account;

import io.reactivex.Single;

public class GetAccountUseCase {
    private final AccountRepository accountRepository;

    public GetAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Single<Account> getAccount() {
        return accountRepository.getAccount();
    }
}
