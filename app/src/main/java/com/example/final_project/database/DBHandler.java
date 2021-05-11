package com.example.final_project.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper
{
    public static final String TABLE_NAME = "score";
    public static final String COL_NAME = "username";
    public static final String COL_SCORE = "score";

    public DBHandler(Context context) {super(context, "leaderboards.db", null, 1);}

    @Override public void onCreate(SQLiteDatabase db)
    {
        String create_table = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " + COL_NAME + " CHAR," + COL_SCORE + "INT)";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addPlayerData(String username, int score)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, username);
        values.put(COL_SCORE, score);
        long data = database.insert(TABLE_NAME, null, values);
        if(data > 0) {return true;} else {return false;}
    }

    public List<DBOutput> getUserData()
    {
        List<DBOutput> returnData = new ArrayList<>();
        String get_data = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_SCORE + " ASC";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(get_data, null);

        if(cursor.moveToFirst())
        {
            do {
                String username = cursor.getString(0); int score = cursor.getInt(1);
                DBOutput input = new DBOutput(username, score); returnData.add(input);
            }
            while (cursor.moveToNext());
        }
        cursor.close(); database.close(); return returnData;
    }

    public boolean deleteUserData(DBOutput dbOutput)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String delete = "DELETE FROM scores WHERE username = '" + dbOutput.getUsername() + "'";
        Cursor cursor = database.rawQuery(delete, null);
        if(cursor.moveToFirst()) { return true; } else  { return false; }
    }

}
