package com.team2.packpackmonsters.data;

/**
 * Created by Owner on 4/22/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class MonsterMoveDbHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "MonsterMove.db";//Name of the database

    private static final int DATABASE_VERSION = 1;//Don't worry about

    public MonsterMoveDbHelper(Context context)//Constructor for UserProfileHelperDb Class
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)//We are creating the database for the first time here...
    {
        String SQL_CREATE_MONSTER_MOVE_TABLE = "CREATE TABLE " + PacPacMonstersContract.ItemsEntry.TABLE_NAME +//Created table for User Profile
                "(" + PacPacMonstersContract.MonsterMoveEntry.COLUMN_MONSTER_ID + " INTEGER NOT NULL, " +//ID field row
                PacPacMonstersContract.MonsterMoveEntry.COLUMN_MOVE_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY " + (PacPacMonstersContract.MonsterMoveEntry.COLUMN_MOVE_ID) + " REFERENCES " + PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME +
                "(" + PacPacMonstersContract.PacPacMonsterEntry._ID + ")," + " FOREIGN KEY " + (PacPacMonstersContract.MonsterMoveEntry.COLUMN_MOVE_ID) +
                " REFERENCES " + PacPacMonstersContract.MovesEntry.TABLE_NAME + "(" + PacPacMonstersContract.MovesEntry._ID + "));";//Name of Item
        db.execSQL(SQL_CREATE_MONSTER_MOVE_TABLE);//Actually creates the table using the execSQL method, takes the db variable
        // In the constructor for onCreate to operate on the non static method execSQL and we
        //pass it our data table
        db.execSQL("PRAGMA foreign_keys = TRUE;");//Possible error here
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)//Called when the database has to be updated...
    {

    }



}
