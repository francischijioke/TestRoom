package com.zinitytech.testroom.DB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RecordViewModel extends AndroidViewModel {


    RecordRepo recordRepo;

    //linkup list
    private LiveData<List<RecordTB>> users;


    public RecordViewModel(@NonNull Application application) {
        super(application);
        recordRepo = new RecordRepo(application);
        users = recordRepo.getUsers();

    }


    public LiveData<List<RecordTB>> getUsers() {
        return users;
    }

    public void insertRecord(RecordTB recordTB) {
        recordRepo.insertRecord(recordTB);
    }



    public void updateRecord(String name, String email, String phone) {
         recordRepo.updateRecord(name, email, phone);
    }

    public void updateRecord(RecordTB recordTB) {
        recordRepo.updateRecord(recordTB);
    }

    public  RecordTB getRecord(String email) {
        return recordRepo.getRecord(email);
    }

}
