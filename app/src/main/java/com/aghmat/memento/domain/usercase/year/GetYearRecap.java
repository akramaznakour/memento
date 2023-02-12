package com.aghmat.memento.domain.usercase.year;

import com.aghmat.memento.data.repository.AccountRepository;
import com.aghmat.memento.domain.enums.AgeType;
import com.aghmat.memento.domain.model.YearRecap;

import java.time.LocalDate;

import io.reactivex.Observable;

public class GetYearRecap {
    private final AccountRepository accountRepository;

    public GetYearRecap(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Observable<YearRecap> getYearRecap(int age) {
        return accountRepository.getAccount()
                .flatMapObservable(account -> {
                    LocalDate birthDate = account.getBirthDate();
                    YearRecap yearRecap = new YearRecap();
                    yearRecap.setAge(age);
                    yearRecap.setStartYear(birthDate.getYear() + age);
                    yearRecap.setEndYear(birthDate.getYear() + age + 1);

                    LocalDate currentYear = birthDate.plusYears(age);
                    LocalDate currentDate = LocalDate.now();

                    if (currentYear.isBefore(currentDate)) {
                        yearRecap.setYearType(AgeType.PASSED);
                    } else if (currentYear.isEqual(currentDate)) {
                        yearRecap.setYearType(AgeType.CURRENT);
                    } else {
                        yearRecap.setYearType(AgeType.FUTURE);
                    }

                    return Observable.just(yearRecap);
                });
    }

}
