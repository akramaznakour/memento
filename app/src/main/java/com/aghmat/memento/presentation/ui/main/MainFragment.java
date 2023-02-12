package com.aghmat.memento.presentation.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.aghmat.memento.R;
import com.aghmat.memento.common.NavigationConstants;
import com.aghmat.memento.databinding.FragmentMainBinding;
import com.aghmat.memento.domain.model.Age;
import com.aghmat.memento.domain.model.YearRecap;
import com.aghmat.memento.presentation.ui.base.BaseFragment;
import com.aghmat.memento.presentation.ui.main.adapter.YearRecapAdapter;

import java.util.List;

public class MainFragment extends BaseFragment {

    private FragmentMainBinding binding;
    private MainViewModel viewModel;
    private YearRecapAdapter adapter;

    @Override
    public int getCurrentDestinationId() {
        return R.id.mainFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new YearRecapAdapter(this::onYearRecapClicked);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.fetchAge();
        viewModel.fetchYearsRecap();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setAdapter(adapter);

        viewModel.getAge().observe(getViewLifecycleOwner(), this::onAgeUpdated);
        viewModel.getYearRecaps().observe(getViewLifecycleOwner(), this::onYearRecapsUpdated);
    }

    private void onYearRecapsUpdated(List<YearRecap> yearRecaps) {
        adapter.setYearRecaps(yearRecaps);
    }

    private void onAgeUpdated(Age age) {
        String yearsText = getResources().getQuantityString(
                R.plurals.screen_main_years_count,
                age.getYears(),
                age.getYears());
        String monthsText = getResources().getQuantityString(
                R.plurals.screen_main_months_count,
                age.getMonths(),
                age.getMonths());
        String daysText = getResources().getQuantityString(
                R.plurals.screen_main_days_count,
                age.getDays(),
                age.getDays());
        String text = getString(R.string.screen_main_age_header, yearsText, monthsText, daysText);
        binding.ageTextView.setText(text);
    }

    private void onYearRecapClicked(YearRecap yearRecap) {
        Bundle bundle = new Bundle();
        bundle.putInt(NavigationConstants.YEAR_ARG, yearRecap.getAge());
        navigate(R.id.action_mainFragment_to_yearDetailsFragment, bundle);
    }
}
