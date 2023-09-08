package com.zinitytech.testroom.DB;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {RecordTB.class},
        version = 2,
        exportSchema = true,
        autoMigrations = {
                @AutoMigration (from = 1, to = 2)
        }
)

public abstract class RecordDB extends RoomDatabase {

 public abstract RecordDao Dao();

 // below line is to create instance
 // for our database class.
 private static RecordDB instance;

 // on below line we are getting instance for our database.
 public static synchronized RecordDB getInstance(Context context) {
  // below line is to check if
  // the instance is null or not.
  if (instance == null) {
   // if the instance is null we
   // are creating a new instance
   instance =
           // for creating a instance for our database
           // we are creating a database builder and passing
           // our database class with our database name.
           Room.databaseBuilder(context.getApplicationContext(),
                           RecordDB.class, "records")
                   // below line is use to add fall back to
                   // destructive migration to our database.
                   .fallbackToDestructiveMigration()
                   //.addMigrations(migration)
                   // below line is to
                   // build our database.
                   .build();
  }
  // after creating an instance
  // we are returning our instance
  return instance;

 }

}
