package com.zinitytech.testroom.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zinitytech.testroom.Records;

import java.util.List;

@Dao
public interface RecordDao {

    @Query("SELECT * FROM users")
    LiveData<List<RecordTB>>  getUsers();


  @Insert
  Void insertRecord(RecordTB recordTB);

  @Delete
    void deleteUser(RecordTB recordTB);

    @Update
    Void updateRecord(RecordTB recordTB);

    @Query("UPDATE users SET name = :name , phone = :phone where email = :email")
    void updateRecord(String name, String email, String phone);

    @Query("SELECT * FROM users where email = :email LIMIT 1")
    RecordTB getRecord(String email);

}
