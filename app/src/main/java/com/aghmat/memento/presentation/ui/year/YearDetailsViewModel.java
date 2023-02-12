package com.aghmat.memento.presentation.ui.year;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aghmat.memento.data.database.AppDatabase;
import com.aghmat.memento.data.repository.AccountRepository;
import com.aghmat.memento.domain.model.WeekRecap;
import com.aghmat.memento.domain.model.YearRecap;
import com.aghmat.memento.domain.usercase.week.GetWeekRecaps;
import com.aghmat.memento.domain.usercase.year.GetYearRecap;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class YearDetailsViewModel extends AndroidViewModel {

    private final GetYearRecap getYearRecap;
    private final GetWeekRecaps getWeekRecaps;

    private final MutableLiveData<YearRecap> yearRecap = new MutableLiveData<>();
    private final MutableLiveData<List<WeekRecap>> weekRecaps = new MutableLiveData<>();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private int age;

    public YearDetailsViewModel(@NonNull Application application) {
        super(application);
        AccountRepository accountRepository = new AccountRepository(AppDatabase.getInstance(application));

        getYearRecap = new GetYearRecap(accountRepository);
        getWeekRecaps = new GetWeekRecaps(accountRepository);
    }

    public void fetchYearRecap(int age) {
        Disposable disposable = getYearRecap.getYearRecap(age)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(YearDetailsViewModel.this.yearRecap::setValue, throwable -> {
                });

        compositeDisposable.add(disposable);
    }

    public void fetchWeeksRecap(int age) {
        Disposable disposable = getWeekRecaps.getWeekRecaps(age)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(YearDetailsViewModel.this.weekRecaps::setValue, throwable -> {
                });

        compositeDisposable.add(disposable);
    }

    public LiveData<YearRecap> getYearRecap() {
        return yearRecap;
    }

    public LiveData<List<WeekRecap>> getWeekRecaps() {
        return weekRecaps;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
