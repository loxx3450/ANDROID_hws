package com.loxx3450.hw_11_04_25;

import android.app.Application;

import androidx.room.Room;

import com.loxx3450.hw_11_04_25.db.AppDatabase;

public class App extends Application {
    private static App instance;

    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "library_db")
                .build();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public static App getInstance() {
        return instance;
    }
}
