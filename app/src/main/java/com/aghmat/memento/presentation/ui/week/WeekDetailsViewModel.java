package com.aghmat.memento.presentation.ui.week;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aghmat.memento.data.database.AppDatabase;
import com.aghmat.memento.data.repository.AccountRepository;
import com.aghmat.memento.domain.model.WeekRecap;
import com.aghmat.memento.domain.usercase.week.GetWeekRecap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeekDetailsViewModel extends AndroidViewModel {

    private final GetWeekRecap getWeekRecap;

    private final MutableLiveData<WeekRecap> weekRecap = new MutableLiveData<>();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public WeekDetailsViewModel(@NonNull Application application) {
        super(application);
        AccountRepository accountRepository = new AccountRepository(AppDatabase.getInstance(application));

        getWeekRecap = new GetWeekRecap(accountRepository);
    }

    public void fetchWeekRecap(int age, int weekNumber) {
        Disposable disposable = getWeekRecap.getWeekRecap(age, weekNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(WeekDetailsViewModel.this.weekRecap::setValue, throwable -> {
                });

        compositeDisposable.add(disposable);
    }

    public LiveData<WeekRecap> getWeekRecap() {
        return weekRecap;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
