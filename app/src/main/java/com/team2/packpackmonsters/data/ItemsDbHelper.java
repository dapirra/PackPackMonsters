package com.team2.packpackmonsters.data;

/**
 * Created by Owner on 4/22/2018.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemsDbHelper extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME = "Items.db";//Name of the database

    private static final int DATABASE_VERSION = 1;//Don't worry about

    public ItemsDbHelper(Context context)//Constructor for UserProfileHelperDb Class
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)//We are creating the database for the first time here...
    {
        String SQL_CREATE_ITEMS_TABLE = "CREATE TABLE " + PacPacMonstersContract.ItemsEntry.TABLE_NAME +//Created table for User Profile
                "(" + PacPacMonstersContract.ItemsEntry._ID + " INTEGER AUTO INCREMENT, " +//ID field row
                PacPacMonstersContract.ItemsEntry.COLUMN_ITEM_NAME + " INTEGER);"; //Name of Item
        db.execSQL(SQL_CREATE_ITEMS_TABLE);//Actually creates the table using the execSQL method, takes the db variable
        // In the constructor for onCreate to operate on the non static method execSQL and we
        //pass it our data table

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)//Called when the database has to be updated...
    {

    }



}
