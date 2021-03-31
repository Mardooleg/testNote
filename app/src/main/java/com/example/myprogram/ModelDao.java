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
    @Query("SELECT* FROM Notatka WHERE title LIKE '%' || :name || '%' OR note LIKE '%' || :name || '%'")
    List<Notatka> getAll(   String name);

    @Query("SELECT* FROM Notatka WHERE favorite = 1 AND (title LIKE '%' || :name || '%' OR note LIKE '%' || :name || '%')")
    List<Notatka> getAllFavorite( String name);

    @Query("SELECT * FROM Notatka WHERE id = :id")
Notatka getById(int id);


    @Insert
    void save(Notatka notatka);

    @Update
    void update(Notatka notatka);

    @Delete
    void delete(Notatka notatka);

    }

