package com.aghmat.memento.presentation.ui.loading;

public class LoadingScreenState {

    private final boolean loading;
    private final Boolean hasAccount;

    public LoadingScreenState(boolean loading, Boolean hasAccount) {
        this.loading = loading;
        this.hasAccount = hasAccount;
    }

    public boolean isLoading() {
        return loading;
    }

    public Boolean getHasAccount() {
        return hasAccount;
    }
}
