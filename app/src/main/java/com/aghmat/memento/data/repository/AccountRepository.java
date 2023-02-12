package com.aghmat.memento.data.repository;

import com.aghmat.memento.data.database.AppDatabase;
import com.aghmat.memento.domain.model.Account;

import java.time.LocalDate;

import io.reactivex.Completable;
import io.reactivex.Single;

public class AccountRepository {

    final AppDatabase appDatabase;

    public AccountRepository(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public Single<Account> getAccount() {
        return appDatabase.getAccountDao().getAccount();
    }

    public Completable createAccount(LocalDate birthDate, int lifeExpectancy) {

        Account account = new Account();
        account.setBirthDate(birthDate);
        account.setLifeExpectancy(lifeExpectancy);

        return appDatabase.getAccountDao().create(account);
    }

}
