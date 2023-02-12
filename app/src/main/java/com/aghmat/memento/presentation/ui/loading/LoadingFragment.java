package com.aghmat.memento.presentation.ui.loading;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.aghmat.memento.R;
import com.aghmat.memento.databinding.FragmentLoadingBinding;
import com.aghmat.memento.presentation.ui.base.BaseFragment;


public class LoadingFragment extends BaseFragment {

    private FragmentLoadingBinding binding;
    private LoadingViewModel viewModel;

    @Override
    public int getCurrentDestinationId() {
        return R.id.loadingFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(LoadingViewModel.class);
        viewModel.fetchAccount();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoadingBinding.inflate(getLayoutInflater());
        viewModel.getLoadingScreenState().observe(getViewLifecycleOwner(), this::onLoadingScreenStateUpdated);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void onLoadingScreenStateUpdated(LoadingScreenState loadingScreenState) {
        if (loadingScreenState.isLoading()) {
            binding.loaderLottieAnimationView.setVisibility(View.VISIBLE);
        } else if (loadingScreenState.getHasAccount()) {
            binding.loaderLottieAnimationView.setVisibility(View.GONE);
            navigate(R.id.action_loadingFragment_to_mainFragment);
        } else {
            binding.loaderLottieAnimationView.setVisibility(View.GONE);
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loadingFragment_to_accountFragment);
        }

    }
}
