package com.example.myprogram;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Notatka {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title; // название
    private String note;  // столица
    private boolean favorite; // ресурс флага

    public Notatka(String title, String note, boolean favorite){

        this.title=title;
        this.note=note;
        this.favorite=favorite;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
