package com.aghmat.memento.presentation.ui.account;

public class AccountCreationResult {

    private final boolean success;
    private final int errorMessage;

    private AccountCreationResult(boolean success, int errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getErrorMessage() {
        return errorMessage;
    }

    public static AccountCreationResult success() {
        return new AccountCreationResult(true, 0);
    }

    public static AccountCreationResult failure(int errorMessage) {
        return new AccountCreationResult(false, errorMessage);
    }
}
