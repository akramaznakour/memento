package com.aghmat.memento.presentation.validation;

import androidx.annotation.StringRes;

public class ValidationError {

    @StringRes
    private final int fieldName;

    @StringRes
    private final int message;

    public ValidationError(int fieldName, int message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public int getFieldName() {
        return fieldName;
    }

    public int getMessage() {
        return message;
    }
}
