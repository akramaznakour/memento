package com.aghmat.memento.presentation.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aghmat.memento.data.database.AppDatabase;
import com.aghmat.memento.data.repository.AccountRepository;
import com.aghmat.memento.domain.model.Age;
import com.aghmat.memento.domain.model.YearRecap;
import com.aghmat.memento.domain.usercase.account.GetAgeUseCase;
import com.aghmat.memento.domain.usercase.year.GetYearRecaps;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private final GetAgeUseCase getAgeUseCase;
    private final GetYearRecaps getYearRecaps;

    private final MutableLiveData<Age> age = new MutableLiveData<>();
    private final MutableLiveData<List<YearRecap>> yearRecaps = new MutableLiveData<>();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MainViewModel(@NonNull Application application) {
        super(application);
        AccountRepository accountRepository = new AccountRepository(AppDatabase.getInstance(application));

        getAgeUseCase = new GetAgeUseCase(accountRepository);
        getYearRecaps = new GetYearRecaps(accountRepository);
    }

    public void fetchAge() {
        Disposable disposable = getAgeUseCase.getAge()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(MainViewModel.this.age::setValue, throwable -> {
                });

        compositeDisposable.add(disposable);
    }

    public void fetchYearsRecap() {
        Disposable disposable = getYearRecaps.getYearsRecap()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(MainViewModel.this.yearRecaps::setValue, throwable -> {
                });

        compositeDisposable.add(disposable);
    }

    public LiveData<Age> getAge() {
        return age;
    }

    public LiveData<List<YearRecap>> getYearRecaps() {
        return yearRecaps;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
