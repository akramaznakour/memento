package com.aghmat.memento.presentation.ui.year;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.aghmat.memento.R;
import com.aghmat.memento.common.NavigationConstants;
import com.aghmat.memento.databinding.FragmentYearDetailsBinding;
import com.aghmat.memento.domain.model.WeekRecap;
import com.aghmat.memento.domain.model.YearRecap;
import com.aghmat.memento.presentation.ui.base.BaseFragment;
import com.aghmat.memento.presentation.ui.year.adapter.WeekRecapAdapter;

import java.util.List;

public class YearDetailsFragment extends BaseFragment {

    private FragmentYearDetailsBinding binding;
    private YearDetailsViewModel viewModel;
    private WeekRecapAdapter adapter;

    @Override
    public int getCurrentDestinationId() {
        return R.id.yearDetailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int age = requireArguments().getInt(NavigationConstants.YEAR_ARG);

        viewModel.setAge(age);

        adapter = new WeekRecapAdapter(this::onWeekRecapClicked);

        viewModel = new ViewModelProvider(this).get(YearDetailsViewModel.class);
        viewModel.fetchYearRecap(age);
        viewModel.fetchWeeksRecap(age);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentYearDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setAdapter(adapter);

        viewModel.getYearRecap().observe(getViewLifecycleOwner(), this::onYearRecapUpdated);
        viewModel.getWeekRecaps().observe(getViewLifecycleOwner(), this::onWeekRecapsUpdated);
    }

    private void onWeekRecapClicked(WeekRecap weekRecap) {
        Bundle bundle = new Bundle();
        bundle.putInt(NavigationConstants.YEAR_ARG, viewModel.getAge());
        bundle.putInt(NavigationConstants.WEEK_NUMBER_ARG, weekRecap.getWeekNumber());
        navigate(R.id.action_yearDetailsFragment_to_weekDetailsFragment, bundle);
    }

    private void onWeekRecapsUpdated(List<WeekRecap> weekRecaps) {
        adapter.setWeekRecaps(weekRecaps);
    }

    private void onYearRecapUpdated(YearRecap yearRecap) {
        binding.yearTextView.setText(getString(R.string.screen_year_details_year_header, yearRecap.getAge()));
    }
}
