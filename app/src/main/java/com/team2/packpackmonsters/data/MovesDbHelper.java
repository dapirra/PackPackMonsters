package com.team2.packpackmonsters.data;

/**
 * Created by Owner on 4/21/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class MovesDbHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Moves.db";//Name of the database

    private static final int DATABASE_VERSION = 1;//Don't worry about
    private static boolean loadDataOnce = false;

    public MovesDbHelper(Context context)//Constructor for MonsterHelperDb Class
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)//We are creating the database for the first time here...
    {

            String SQL_CREATE_MOVES_TABLE = "CREATE TABLE " + PacPacMonstersContract.MovesEntry.TABLE_NAME +//Created table for User Profile
                    "(" + PacPacMonstersContract.MovesEntry._ID + " INTEGER AUTO INCREMENT, " +//ID field row
                    PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME + " TEXT NOT NULL, " + //Name field, can't be null
                    PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE + " INTEGER NOT NULL, " +//Move Damage
                    PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF + " INTEGER);";//Move Buff

            db.execSQL(SQL_CREATE_MOVES_TABLE);//Actually creates the table using the execSQL method, takes the db variable
            // In the constructor for onCreate to operate on the non static method execSQL and we
            //pass it our data table

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)//Called when the database has to be updated...
    {

    }

}
