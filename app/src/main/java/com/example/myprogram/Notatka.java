package com.example.myprogram;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Collections;

@Entity
public class Notatka implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String note;
    private boolean passcode;
    private boolean favorite;

    public Notatka(String title, String note, boolean passcode, boolean favorite){

        this.title=title;
        this.note=note;
        this.passcode=passcode;
        this.favorite=favorite;

    }


    protected Notatka(Parcel in) {
        id = in.readInt();
        title = in.readString();
        note = in.readString();
        passcode = in.readByte() != 0;
        favorite = in.readByte() != 0;
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(note);
        dest.writeByte((byte) (passcode ? 1 : 0));
        dest.writeByte((byte) (favorite ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Notatka> CREATOR = new Creator<Notatka>() {
        @Override
        public Notatka createFromParcel(Parcel in) {
            return new Notatka(in);
        }

        @Override
        public Notatka[] newArray(int size) {
            return new Notatka[size];
        }
    };

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

    public boolean getPasscode() {
        return passcode;
    }

    public void setPasscode(boolean note) {
        this.passcode = passcode;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
