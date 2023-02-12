package com.aghmat.memento.presentation.ui.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.aghmat.memento.R;
import com.aghmat.memento.databinding.FragmentAccountBinding;
import com.aghmat.memento.presentation.ui.base.BaseFragment;
import com.aghmat.memento.presentation.validation.ValidationError;
import com.aghmat.memento.presentation.validation.account.BirthDataValidationResult;

public class AccountFragment extends BaseFragment {

    private FragmentAccountBinding binding;
    private AccountViewModel viewModel;

    @Override
    public int getCurrentDestinationId() {
        return R.id.accountFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requireActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        viewModel = new ViewModelProvider(this).get(AccountViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.saveButton.setOnClickListener(v -> onSaveButtonClicked());
        viewModel.getAccountCreationResult().observe(getViewLifecycleOwner(), this::onAccountCreationResultUpdated);
    }

    private void onSaveButtonClicked() {
        String year = binding.brithYear.getText().toString();
        String month = binding.brithMonthEditText.getText().toString();
        String day = binding.brithDayEditText.getText().toString();
        String lifeExpectancy = binding.lifeExpectancyEditText.getText().toString();

        BirthDataValidationResult birthDataValidationResult = viewModel.validateAccount(year, month, day, lifeExpectancy);

        if (birthDataValidationResult.isSuccess()) {
            viewModel.createAccount(year, month, day, lifeExpectancy);
        } else {
            for (ValidationError validationError : birthDataValidationResult.getValidationErrors()) {
                Toast.makeText(getContext(),
                        String.format("%s %s",
                                requireContext().getString(validationError.getFieldName()),
                                requireContext().getString(validationError.getMessage())),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void onAccountCreationResultUpdated(AccountCreationResult accountCreationResult) {
        if (accountCreationResult != null) {
            if (accountCreationResult.isSuccess()) {
                Toast.makeText(getContext(), requireContext().getString(R.string.screen_account_account_created_successfully), Toast.LENGTH_SHORT).show();
                navigate(R.id.action_accountFragment_to_mainFragment);
            } else {
                Toast.makeText(getContext(), accountCreationResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}

