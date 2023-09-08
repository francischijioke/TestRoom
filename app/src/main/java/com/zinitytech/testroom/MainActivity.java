package com.zinitytech.testroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.zinitytech.testroom.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.register.setOnClickListener((v) -> {
            startActivity(new Intent(MainActivity.this, Register.class));
        });


        binding.view.setOnClickListener((v) -> {
            startActivity(new Intent(MainActivity.this, Records.class));
        });









    }









}