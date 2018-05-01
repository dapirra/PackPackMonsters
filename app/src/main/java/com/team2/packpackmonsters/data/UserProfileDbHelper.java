package com.team2.packpackmonsters.data;

/**
 * Created by Owner on 4/20/2018.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserProfileDbHelper extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME = "UserProfile.db";//Name of the database

    private static final int DATABASE_VERSION = 1;//Don't worry about

    public UserProfileDbHelper(Context context)//Constructor for UserProfileHelperDb Class
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)//We are creating the database for the first time here...
    {
        String SQL_CREATE_USERPROFILE_TABLE = "CREATE TABLE " + PacPacMonstersContract.UserProfileEntry.TABLE_NAME +//Created table for User Profile
                "(" + PacPacMonstersContract.UserProfileEntry._ID + " INTEGER AUTO INCREMENT, " +//ID field row
                PacPacMonstersContract.UserProfileEntry.COLUMN_NAME + " TEXT NOT NULL, " + //Name field, can't be null
                PacPacMonstersContract.UserProfileEntry.COLUMN_WINS + " INTEGER NOT NULL DEFAULT 0, " +//Wins field, can't be null and = 0 to start
                PacPacMonstersContract.UserProfileEntry.COLUMN_LOSSES + " INTEGER NOT NULL DEFAULT 0, " +//Losses
                PacPacMonstersContract.UserProfileEntry.COLUMN_SURRENDERS + " INTEGER NOT NULL DEFAULT 0);";//Surrenders

        db.execSQL(SQL_CREATE_USERPROFILE_TABLE);//Actually creates the table using the execSQL method, takes the db variable
        // In the constructor for onCreate to operate on the non static method execSQL and we
        //pass it our data table

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)//Called when the database has to be updated...
    {

    }

}
