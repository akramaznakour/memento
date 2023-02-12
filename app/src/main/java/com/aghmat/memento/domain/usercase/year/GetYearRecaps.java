package com.aghmat.memento.domain.usercase.year;

import com.aghmat.memento.data.repository.AccountRepository;
import com.aghmat.memento.domain.enums.AgeType;
import com.aghmat.memento.domain.model.YearRecap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class GetYearRecaps {
    private final AccountRepository accountRepository;

    public GetYearRecaps(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Observable<List<YearRecap>> getYearsRecap() {
        return accountRepository.getAccount()
                .flatMapObservable(account -> {
                    LocalDate birthDate = account.getBirthDate();
                    int lifeExpectancyInYears = account.getLifeExpectancy();
                    List<YearRecap> yearRecaps = new ArrayList<>();

                    for (int i = 0; i < lifeExpectancyInYears; i++) {
                        YearRecap yearRecap = new YearRecap();
                        yearRecap.setAge(i);
                        yearRecap.setStartYear(birthDate.getYear() + i);
                        yearRecap.setEndYear(birthDate.getYear() + i + 1);

                        LocalDate currentYear = birthDate.plusYears(i);
                        LocalDate currentDate = LocalDate.now();

                        if (currentYear.isBefore(currentDate)) {
                            yearRecap.setYearType(AgeType.PASSED);
                        } else if (currentYear.isEqual(currentDate)) {
                            yearRecap.setYearType(AgeType.CURRENT);
                        } else {
                            yearRecap.setYearType(AgeType.FUTURE);
                        }

                        yearRecaps.add(yearRecap);
                    }

                    return Observable.just(yearRecaps);
                });
    }

}
