package com.team2.packpackmonsters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.team2.packpackmonsters.data.MonstersDbHelper;
import com.team2.packpackmonsters.data.MovesDbHelper;
import com.team2.packpackmonsters.data.PacPacMonstersContract;
import com.team2.packpackmonsters.data.UserProfileDbHelper;

public class MainActivity extends AppCompatActivity {

    private UserProfileDbHelper MDbHelper;
    private MonstersDbHelper ADbHelper;
    private MovesDbHelper BDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //displayDatabaseInfo();
        //displayDatabaseInfo2();
        //displayDatabaseInfo3();
        ADbHelper = new MonstersDbHelper(this);
        MDbHelper = new UserProfileDbHelper(this);
        insertMonsters();
        displayDatabaseInfo2();
    }

    private void displayDatabaseInfo() {//TEST TO SEE IF DATABASE WORKS...

        UserProfileDbHelper mDbHelper = new UserProfileDbHelper(this);//creating an instance of the helper

        SQLiteDatabase db = mDbHelper.getReadableDatabase();//Opening the database or creating a new one

        Cursor cursor = db.rawQuery("SELECT * FROM " + PacPacMonstersContract.UserProfileEntry.TABLE_NAME, null);
        try {
            TextView displayView = (TextView) findViewById(R.id.text_view_test);
            displayView.setText("Number of rows in user profile database table: " + cursor.getCount());
        } finally {
            cursor.close();
        }
    }

    private void displayDatabaseInfo2() {//TEST TO SEE IF DATABASE WORKS...

        //creating an instance of the helper

        SQLiteDatabase db = ADbHelper.getReadableDatabase();//Opening the database or creating a new one

        Cursor cursor = db.rawQuery("SELECT * FROM " + PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null);
        try {
            TextView displayView = (TextView) findViewById(R.id.text_view_test2);
            displayView.setText("Number of rows in Monster database table: " + cursor.getCount());
        } finally {
            cursor.close();
        }
    }

    private void displayDatabaseInfo3() {//TEST TO SEE IF DATABASE WORKS...

        MovesDbHelper BDbHelper = new MovesDbHelper(this);//creating an instance of the helper

        SQLiteDatabase db = BDbHelper.getReadableDatabase();//Opening the database or creating a new one

        Cursor cursor = db.rawQuery("SELECT * FROM " + PacPacMonstersContract.MovesEntry.TABLE_NAME, null);
        try {
            TextView displayView = (TextView) findViewById(R.id.text_view_test3);
            displayView.setText("Number of rows in moves database table: " + cursor.getCount());
        } finally {
            cursor.close();
        }
    }

    private void insertMonsters()//Monsters are inserted
    {

        SQLiteDatabase db = ADbHelper.getWritableDatabase();//Database is in writemode
        ContentValues values = new ContentValues();//Content Values object Column names are keys and attributes in "" are values

        //Fire Monster #1
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Fire One");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "1");//Fire is Type 1
        //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
        long newRowId = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);

        //Fire Monster #2
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Fire Two");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "1");//Fire is Type 1
        //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
        newRowId = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);

        //Water Monster #1
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Water One");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "2");//Water is Type 2
        //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
         newRowId = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);
        //Water Monster #2
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Water Two");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "2");//Water is Type 2
        //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
        newRowId = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);

        //Wind Monster #1
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Wind One");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "3");//Wind is Type 3
        //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
        newRowId = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);

        //Wind Monster #2
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Wind Two");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "3");//Wind is Type 3
        //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
        newRowId = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);

        //Earth Monster #1
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Earth One");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "4");//Earth is type 4
        //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
        newRowId = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);

        //Earth Monster #2
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Earth Two");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
        values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "1");//Earth is Type 4
        //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
        newRowId = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);//Actually inserts
        //the data                  ^^^The name of the table inserted                                       ^^Values object
    }
    private void insertUserProfile()//Dummy for now to see if this works, inserts stats /// Use to hardcode for Monsters
    {
        // Gets the database in write mode
        SQLiteDatabase db = MDbHelper.getWritableDatabase();//Write mode is to CREATE, UPDATE, & DELETE


        ContentValues values = new ContentValues();//Content Values object Column names are keys and attributes in "" are values
        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_NAME, "John Snow");
        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_WINS, "5");
        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_LOSSES, "2");
        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_SURRENDERS, "3");

        long newRowId = db.insert(PacPacMonstersContract.UserProfileEntry.TABLE_NAME, null, values);//Actually inserts
        //the data                  ^^^The name of the table inserted                                       ^^Values object
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)//Inflates the menu option
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) //User clicked on menu item
    {
        switch (item.getItemId())
        {
            case R.id.action_show_stats://Right now for debugging, click on show stats and displays stats but for now, just # of rows
                insertUserProfile();
                displayDatabaseInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
