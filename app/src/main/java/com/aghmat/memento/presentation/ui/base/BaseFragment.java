package com.aghmat.memento.presentation.ui.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


public abstract class BaseFragment extends Fragment {

    protected NavController navController;

    public abstract int getCurrentDestinationId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    protected void navigate(int action) {
        navigate(getCurrentDestinationId(), action, null);
    }

    protected void navigate(int action, Bundle args) {
        navigate(getCurrentDestinationId(), action, args);
    }

    protected void navigate(int currentDestination, int action, Bundle args) {
        if (navController.getCurrentDestination() != null &&
                navController.getCurrentDestination().getId() == currentDestination) {
            navController.navigate(action, args);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        navController = null;
    }
}
