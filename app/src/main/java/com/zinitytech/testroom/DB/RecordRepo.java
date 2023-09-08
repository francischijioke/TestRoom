package com.zinitytech.testroom.DB;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecordRepo {

private RecordDao recordDao;
private  RecordDB recordDB;

    LiveData<List<RecordTB>> users;



    public RecordRepo(Application application) {
       recordDB =RecordDB.getInstance(application);
      recordDao =recordDB.Dao();
  }


    //linkup list section
    public LiveData<List<RecordTB>> getUsers() {
        if (this.users == null) {
            this.users = recordDao.getUsers();
        }
        return this.users;
    }


    private static class InsertRecordExecutor  {

        private RecordDao recordDao;
        private RecordTB modal;

        private InsertRecordExecutor(RecordDao dao, RecordTB modal) {
            this.recordDao = dao;
            this.modal = modal;
            execute();
        }

        private void execute() {
            ExecutorService executor = Executors.newSingleThreadExecutor();

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    recordDao.insertRecord(modal);
                }
            });
        }


    }

    public void insertRecord(RecordTB modal) {
        new InsertRecordExecutor(recordDao, modal);
    }



    public void updateRecord(String name, String email, String phone) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recordDao.updateRecord(name, email, phone);
            }
        });

    }


    public  RecordTB getRecord(String email) {
        return recordDao.getRecord(email);
    }


    private static class UpdateRecordExecutor  {

        private RecordDao recordDao;
        private RecordTB modal;

        private UpdateRecordExecutor(RecordDao dao, RecordTB modal) {
            this.recordDao = dao;
            this.modal = modal;
            execute();
        }

        private void execute() {
            ExecutorService executor = Executors.newSingleThreadExecutor();

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    recordDao.updateRecord(modal);
                }
            });
        }


    }

    public void updateRecord(RecordTB modal) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                new UpdateRecordExecutor(recordDao, modal);
            }
        });

    }




}
