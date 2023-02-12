package com.aghmat.memento.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.aghmat.memento.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
