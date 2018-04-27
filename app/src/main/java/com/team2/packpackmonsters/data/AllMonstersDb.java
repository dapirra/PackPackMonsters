package com.team2.packpackmonsters.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Owner on 4/27/2018.
 */

public class AllMonstersDb extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "AllMonsters.db"; //Name of the database

    private static final int DATABASE_VERSION = 1 ;//Don't worry about

    public AllMonstersDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
