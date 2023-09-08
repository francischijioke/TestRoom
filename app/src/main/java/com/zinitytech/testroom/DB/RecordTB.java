package com.zinitytech.testroom.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class RecordTB {


    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo
    String name;

    @ColumnInfo
    String email;

    @ColumnInfo(defaultValue = "0")
    String phone;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public RecordTB(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }



   public String getName() {
       return this.name;
   }

   public void setName(String name) {
       this.name = name;
   }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
