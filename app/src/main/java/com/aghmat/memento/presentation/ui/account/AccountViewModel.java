package com.aghmat.memento.presentation.ui.account;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aghmat.memento.R;
import com.aghmat.memento.data.database.AppDatabase;
import com.aghmat.memento.data.repository.AccountRepository;
import com.aghmat.memento.domain.usercase.account.CreateAccountUseCase;
import com.aghmat.memento.presentation.validation.account.BirthData;
import com.aghmat.memento.presentation.validation.account.BirthDataValidationResult;
import com.aghmat.memento.presentation.validation.account.BirthDataValidator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AccountViewModel extends AndroidViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final CreateAccountUseCase createAccountUseCase;

    public final MutableLiveData<AccountCreationResult> accountCreationResult = new MutableLiveData<>();

    public AccountViewModel(@NonNull Application application) {
        super(application);

        AccountRepository accountRepository = new AccountRepository(AppDatabase.getInstance(application));

        createAccountUseCase = new CreateAccountUseCase(accountRepository);
    }

    public void createAccount(String year, String month, String day, String lifeExpectancy) {
        Disposable disposable = createAccountUseCase.createAccount(year, month, day, lifeExpectancy)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> accountCreationResult.setValue(AccountCreationResult.success()),
                        throwable -> accountCreationResult.setValue(AccountCreationResult.failure(R.string.screen_account_error_creating_account)));

        compositeDisposable.add(disposable);
    }

    public BirthDataValidationResult validateAccount(String year, String month, String day, String lifeExpectancy) {
        BirthData data = new BirthData(year, month, day, lifeExpectancy);
        return new BirthDataValidator().validate(data);
    }

    public LiveData<AccountCreationResult> getAccountCreationResult() {
        return accountCreationResult;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
