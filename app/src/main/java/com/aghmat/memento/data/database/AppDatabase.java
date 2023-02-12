package com.aghmat.memento.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.aghmat.memento.data.dao.AccountDao;
import com.aghmat.memento.domain.model.Account;

@TypeConverters(LocalDateConverter.class)
@Database(entities = {Account.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "memento-db";

    private static AppDatabase appDatabase;

    public abstract AccountDao getAccountDao();

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
        }
        return appDatabase;
    }
}
