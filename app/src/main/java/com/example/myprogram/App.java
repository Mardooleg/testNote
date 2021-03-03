package com.example.myprogram;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    public static App instanse;
    private AppDataBase appDatabase;
    @Override
    public void onCreate() {
        instanse = this;
        appDatabase = Room.databaseBuilder(this,AppDataBase.class,"Bazadannux")
                .allowMainThreadQueries()
                .build();

        // TODO: 02.03.2021  
        //appDatabase.modelDao().save(new Notatka("Yura", "Yura1", false));
        super.onCreate();
    }
    public static App getInstance(){
        return instanse;
    }
    public AppDataBase getAppDatabase(){
        return appDatabase;
    }

}