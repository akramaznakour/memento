package com.aghmat.memento.domain.usercase.week;

import com.aghmat.memento.data.repository.AccountRepository;
import com.aghmat.memento.domain.enums.WeekType;
import com.aghmat.memento.domain.model.WeekRecap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class GetWeekRecaps {
    private final AccountRepository accountRepository;

    public GetWeekRecaps(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Observable<List<WeekRecap>> getWeekRecaps(int age) {
        return accountRepository.getAccount()
                .flatMapObservable(account -> {
                    LocalDate currentDate = LocalDate.now();

                    LocalDate startOfAge = account.getBirthDate().plusYears(age);
                    LocalDate endOfAge = account.getBirthDate().plusYears(age + 1);

                    List<WeekRecap> weekRecaps = new ArrayList<>();
                    LocalDate weekStart = startOfAge;

                    while (!weekStart.isAfter(endOfAge)) {
                        LocalDate weekEnd = weekStart.plusWeeks(1);

                        WeekRecap weekRecap = new WeekRecap();
                        weekRecap.setWeekNumber(weekRecaps.size());
                        weekRecap.setStartDate(weekStart);
                        weekRecap.setEndDate(weekEnd);

                        if (weekEnd.isBefore(currentDate)) {
                            weekRecap.setWeekType(WeekType.PASSED);
                        } else if (weekStart.isAfter(currentDate)) {
                            weekRecap.setWeekType(WeekType.FUTURE);
                        } else {
                            weekRecap.setWeekType(WeekType.CURRENT);
                        }

                        weekRecaps.add(weekRecap);

                        weekStart = weekStart.plusWeeks(1);
                    }

                    return Observable.just(weekRecaps);
                });
    }

}
