package com.proed.proedapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "ProEdApp", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table Users(Id integer primary key autoincrement," +
                "Title text,Surname text,Name text, Position text,City text," +
                    "Department text,Username text,Password text,HasChat bit,HasOpenMailAddress bit)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("CREATE UNIQUE INDEX ux_usern_name ON Users(Username)");
    }
}
