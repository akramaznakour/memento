package com.aghmat.memento.domain.usercase.week;

import com.aghmat.memento.data.repository.AccountRepository;
import com.aghmat.memento.domain.enums.WeekType;
import com.aghmat.memento.domain.model.WeekRecap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class GetWeekRecap {
    private final AccountRepository accountRepository;

    public GetWeekRecap(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Observable<WeekRecap> getWeekRecap(int age, int weekNumber) {
        return accountRepository.getAccount()
                .flatMapObservable(account -> {

                    LocalDate birthDate = account.getBirthDate();
                    LocalDate currentDate = LocalDate.now();
                    LocalDate startOfAge = birthDate.plusYears(age);
                    LocalDate weekStart = startOfAge.plusWeeks(weekNumber);
                    LocalDate weekEnd = weekStart.plusWeeks(1);

                    WeekRecap weekRecap = new WeekRecap();
                    weekRecap.setWeekNumber(weekNumber);
                    weekRecap.setStartDate(weekStart);
                    weekRecap.setEndDate(weekEnd);

                    if (weekEnd.isBefore(currentDate)) {
                        weekRecap.setWeekType(WeekType.PASSED);
                    } else if (weekStart.isAfter(currentDate)) {
                        weekRecap.setWeekType(WeekType.FUTURE);
                    } else {
                        weekRecap.setWeekType(WeekType.CURRENT);
                    }

                    return Observable.just(weekRecap);
                });
    }

}
