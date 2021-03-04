package com.example.myprogram;

import android.graphics.ColorSpace;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface ModelDao{
    @Query("SELECT* FROM Notatka")
    List<Notatka> getAll();

    @Query("SELECT* FROM Notatka WHERE favorite = 1")
    List<Notatka> getAllFavorite();

    @Query("SELECT * FROM Notatka WHERE id = :id")
Notatka getById(int id);

    @Insert
    void save(Notatka notatka);

    @Update
    void update(Notatka notatka);

    @Delete
    void delete(Notatka notatka);

    }

