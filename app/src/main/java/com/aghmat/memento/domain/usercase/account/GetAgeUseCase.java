package com.aghmat.memento.domain.usercase.account;

import com.aghmat.memento.data.repository.AccountRepository;
import com.aghmat.memento.domain.model.Age;

import java.time.LocalDate;
import java.time.Period;

import io.reactivex.Single;

public class GetAgeUseCase {
    private final AccountRepository accountRepository;

    public GetAgeUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Single<Age> getAge() {
        return accountRepository.getAccount()
                .map(account -> {
                    LocalDate currentDate = LocalDate.now();

                    Period period = Period.between(account.getBirthDate(), currentDate);

                    Age age = new Age();
                    age.setDays(period.getDays());
                    age.setMonths(period.getMonths());
                    age.setYears(period.getYears());

                    return age;
                });
    }
}
