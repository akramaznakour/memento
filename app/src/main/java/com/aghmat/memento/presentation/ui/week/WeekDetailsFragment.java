package com.aghmat.memento.presentation.ui.week;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.aghmat.memento.R;
import com.aghmat.memento.common.NavigationConstants;
import com.aghmat.memento.databinding.FragmentWeekDetailsBinding;
import com.aghmat.memento.domain.model.WeekRecap;

public class WeekDetailsFragment extends Fragment {

    private FragmentWeekDetailsBinding binding;
    private WeekDetailsViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int age = requireArguments().getInt(NavigationConstants.YEAR_ARG);
        final int weekNumber = requireArguments().getInt(NavigationConstants.WEEK_NUMBER_ARG);

        viewModel = new ViewModelProvider(this).get(WeekDetailsViewModel.class);
        viewModel.fetchWeekRecap(age, weekNumber);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentWeekDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getWeekRecap().observe(getViewLifecycleOwner(), this::onWeekRecapUpdated);
    }

    private void onWeekRecapUpdated(WeekRecap weekRecap) {
        binding.weekTextView.setText(getString(R.string.screen_week_details_week_header, weekRecap.getWeekNumber(), weekRecap.getWeekNumber()));
    }
}
