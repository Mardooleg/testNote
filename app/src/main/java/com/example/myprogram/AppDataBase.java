package com.example.myprogram;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Notatka.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract ModelDao modelDao();


}
