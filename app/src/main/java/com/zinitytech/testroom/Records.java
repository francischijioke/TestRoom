package com.zinitytech.testroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.zinitytech.testroom.DB.RecordTB;
import com.zinitytech.testroom.DB.RecordViewModel;
import com.zinitytech.testroom.databinding.ActivityRecordsBinding;

import java.util.List;

public class Records extends AppCompatActivity {

    ActivityRecordsBinding binding;

    RecordViewModel recordViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecordsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        init();

    }


    private void init() {
        recordViewModel = new ViewModelProvider(this).get(RecordViewModel.class);


        recordViewModel.getUsers().observe(this, recordTBs -> {
            List<RecordTB> users = recordTBs;
            int i = 0;
            for (RecordTB model : users ) {
              //if (i == 0) {
                  binding.name.setText(model.getName());
                  binding.email.setText(model.getEmail());
                  binding.phone.setText(model.getPhone());
                  binding.id.setText(model.getId()+"");
             // }
              //i++;
            }

        });
    }


}