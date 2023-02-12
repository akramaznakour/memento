package com.aghmat.memento.presentation.validation.account;

import com.aghmat.memento.R;
import com.aghmat.memento.presentation.validation.ValidationError;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class BirthDataValidator {

    public BirthDataValidationResult validate(BirthData birthData) {

        if (StringUtils.isEmpty(birthData.getDay())) {
            return BirthDataValidationResult.failure(Collections.singletonList(new ValidationError(R.string.screen_account_day_field, R.string.general_validation_is_required)));
        }

        if (StringUtils.isEmpty(birthData.getMonth())) {
            return BirthDataValidationResult.failure(Collections.singletonList(new ValidationError(R.string.screen_account_month_field, R.string.general_validation_is_required)));
        }

        if (StringUtils.isEmpty(birthData.getYear())) {
            return BirthDataValidationResult.failure(Collections.singletonList(new ValidationError(R.string.screen_account_year_field, R.string.general_validation_is_required)));
        }

        if (StringUtils.isEmpty(birthData.getLifeExpectancy())) {
            return BirthDataValidationResult.failure(Collections.singletonList(new ValidationError(R.string.screen_account_life_expectancy_field, R.string.general_validation_is_required)));
        }

        if (isNotParsable(birthData.getDay())) {
            return BirthDataValidationResult.failure(Collections.singletonList(new ValidationError(R.string.screen_account_day_field, R.string.general_validation_is_invalid)));
        }

        if (isNotParsable(birthData.getMonth())) {
            return BirthDataValidationResult.failure(Collections.singletonList(new ValidationError(R.string.screen_account_month_field, R.string.general_validation_is_invalid)));
        }

        if (isNotParsable(birthData.getYear())) {
            return BirthDataValidationResult.failure(Collections.singletonList(new ValidationError(R.string.screen_account_year_field, R.string.general_validation_is_invalid)));
        }

        if (isNotParsable(birthData.getLifeExpectancy())) {
            return BirthDataValidationResult.failure(Collections.singletonList(new ValidationError(R.string.screen_account_life_expectancy_field, R.string.general_validation_is_invalid)));
        }

        List<ValidationError> errors = new ArrayList<>();

        int day = Integer.parseInt(birthData.getDay());
        int month = Integer.parseInt(birthData.getMonth());
        int year = Integer.parseInt(birthData.getYear());
        int lifeExpectancy = Integer.parseInt(birthData.getLifeExpectancy());

        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        try {
            calendar.getTime();
        } catch (IllegalArgumentException e) {
            errors.add(new ValidationError(R.string.screen_account_dates_field, R.string.general_validation_error_invalid_date));
        }

        if (lifeExpectancy <= 0) {
            errors.add(new ValidationError(R.string.screen_account_life_expectancy_field, R.string.screen_account_error_invalid_life_expectancy));
        }

        if (errors.isEmpty()) {
            return BirthDataValidationResult.success();
        } else {
            return BirthDataValidationResult.failure(errors);
        }

    }

    private boolean isNotParsable(String integer) {
        try {
            Integer.parseInt(integer);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
