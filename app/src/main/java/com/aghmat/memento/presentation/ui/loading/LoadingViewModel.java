package com.aghmat.memento.presentation.ui.loading;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aghmat.memento.data.database.AppDatabase;
import com.aghmat.memento.data.repository.AccountRepository;
import com.aghmat.memento.domain.usercase.account.GetAccountUseCase;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoadingViewModel extends AndroidViewModel {

    private final GetAccountUseCase getAccountUseCase;

    private final MutableLiveData<LoadingScreenState> loadingScreenState = new MutableLiveData<>();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LoadingViewModel(@NonNull Application application) {
        super(application);

        AccountRepository accountRepository = new AccountRepository(AppDatabase.getInstance(application));

        getAccountUseCase = new GetAccountUseCase(accountRepository);
    }

    public void fetchAccount() {
        loadingScreenState.setValue(new LoadingScreenState(true, null));

        Disposable disposable = getAccountUseCase.getAccount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delaySubscription(2, TimeUnit.SECONDS)
                .subscribe(account -> loadingScreenState.setValue(new LoadingScreenState(false, true)),
                        throwable -> loadingScreenState.setValue(new LoadingScreenState(false, false)));

        compositeDisposable.add(disposable);
    }

    public LiveData<LoadingScreenState> getLoadingScreenState() {
        return loadingScreenState;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
