package com.aghmat.memento.presentation.validation.account;

import com.aghmat.memento.presentation.validation.ValidationError;

import java.util.List;

public class BirthDataValidationResult {

    private final boolean success;

    private final List<ValidationError> validationErrors;

    private BirthDataValidationResult(boolean success, List<ValidationError> validationErrors) {
        this.success = success;
        this.validationErrors = validationErrors;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public static BirthDataValidationResult success() {
        return new BirthDataValidationResult(true, null);
    }

    public static BirthDataValidationResult failure(List<ValidationError> validationErrors) {
        return new BirthDataValidationResult(false, validationErrors);
    }
}
