package com.zinitytech.testroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.zinitytech.testroom.DB.RecordRepo;
import com.zinitytech.testroom.DB.RecordTB;
import com.zinitytech.testroom.DB.RecordViewModel;
import com.zinitytech.testroom.databinding.ActivityRegisterBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Register extends AppCompatActivity {

    private RecordViewModel recordViewModel;
    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.save.setOnClickListener((v) -> {
             if (validateForm()) {

               try {
                   String name =  binding.name.getText().toString().trim();
                   String email =  binding.email.getText().toString().trim();
                   String phone =  binding.phone.getText().toString().trim();
                   RecordTB recordTB = new RecordTB(name, email, phone);
                   recordViewModel.insertRecord(recordTB);
                   Toast.makeText(getApplicationContext(), "Record Added!", Toast.LENGTH_LONG).show();
               } catch (Exception e) {
                   e.printStackTrace();
               }

             } else {
                 Toast.makeText(getApplicationContext(), "Please fill in your data", Toast.LENGTH_LONG).show();
             }
        });


        binding.update.setOnClickListener((v) -> {
            if (validateForm()) {
                String name =  binding.name.getText().toString().trim();
                String email =  binding.email.getText().toString().trim();
                String phone =  binding.phone.getText().toString().trim();


               /* ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        RecordTB recordTB = recordViewModel.getRecord(email);
                       if (recordTB != null) {
                           System.out.println("Record: "+recordTB.getEmail());
                           recordViewModel.updateRecord(recordTB);
                       } else {
                           System.out.println("No Record Found! ");
                       }

                        //recordViewModel.updateRecord(recordTB);
                    }
                });*/
                recordViewModel.updateRecord(name, email , phone);


            } else {
                Toast.makeText(getApplicationContext(), "Please fill in your data", Toast.LENGTH_LONG).show();
            }
        });

    recordViewModel = new ViewModelProvider(this).get(RecordViewModel.class);

    }



    private boolean validateForm() {

        String name =  binding.name.getText().toString().trim();
        String email =  binding.name.getText().toString().trim();

        return (name != null && email != null);
    }





}