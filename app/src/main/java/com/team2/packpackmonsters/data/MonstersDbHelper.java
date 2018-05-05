package com.team2.packpackmonsters.data;

/**
 * Created by Owner on 4/21/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MonstersDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Monsters.db"; //Name of the database

    private static final int DATABASE_VERSION = 1; //Don't worry about

    public MonstersDbHelper(Context context) { //Constructor for MonsterHelperDb Class
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //We are creating the database for the first time here...
        String SQL_CREATE_MONSTERS_TABLE = "CREATE TABLE " + PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME +//Created table for User Profile
                "(" + PacPacMonstersContract.PacPacMonsterEntry._ID + " INTEGER AUTO INCREMENT, " +//ID field row
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME + " TEXT NOT NULL, " + //Name field, can't be null
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP + " INTEGER NOT NULL DEFAULT 50, " +//Hp field
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE + " INTEGER, " +//Type for monster
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE + " TEXT NOT NULL, " +
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_DAMAGE + " INTEGER, " +
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_BUFF + " INTEGER, " +
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO + " TEXT NOT NULL, " +
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_DAMAGE + " INTEGER, " +
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_BUFF + " INTEGER, " +
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE + " TEXT NOT NULL, " +
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_DAMAGE + " INTEGER, " +
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_BUFF + " INTEGER, " +
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR + " TEXT NOT NULL, " +
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_DAMAGE + " INTEGER, " +
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_BUFF + " INTEGER);";
        //PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE + " BLOB);";//Monster Image

        db.execSQL(SQL_CREATE_MONSTERS_TABLE);//Actually creates the table using the execSQL method, takes the db variable
        // In the constructor for onCreate to operate on the non static method execSQL and we
        //pass it our data table
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { //Called when the database has to be updated...
    }
}
