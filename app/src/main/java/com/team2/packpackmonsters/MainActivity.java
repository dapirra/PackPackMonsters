package com.team2.packpackmonsters;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        displayDatabaseInfo();
        displayDatabaseInfo2();
        displayDatabaseInfo3();
    }

    private void displayDatabaseInfo()
    {//TEST TO SEE IF DATABASE WORKS...

        UserProfileDbHelper mDbHelper = new UserProfileDbHelper(this);//creating an instance of the helper

        SQLiteDatabase db = mDbHelper.getReadableDatabase();//Opening the database or creating a new one

        Cursor cursor = db.rawQuery("SELECT * FROM " + PacPacMonstersContract.UserProfileEntry.TABLE_NAME, null);
        try
        {
            TextView displayView = (TextView) findViewById(R.id.text_view_test);
            displayView.setText("Number of rows in user profile database table: " + cursor.getCount());
        }
        finally
        {
            cursor.close();
        }
    }

    private void displayDatabaseInfo2()
    {//TEST TO SEE IF DATABASE WORKS...

        MonstersDbHelper ADbHelper = new MonstersDbHelper(this);//creating an instance of the helper

        SQLiteDatabase db = ADbHelper.getReadableDatabase();//Opening the database or creating a new one

        Cursor cursor = db.rawQuery("SELECT * FROM " + PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null);
        try
        {
            TextView displayView = (TextView) findViewById(R.id.text_view_test2);
            displayView.setText("Number of rows in Monster database table: " + cursor.getCount());
        }
        finally
        {
            cursor.close();
        }
    }

    private void displayDatabaseInfo3()
    {//TEST TO SEE IF DATABASE WORKS...

        MovesDbHelper BDbHelper = new MovesDbHelper(this);//creating an instance of the helper

        SQLiteDatabase db = BDbHelper.getReadableDatabase();//Opening the database or creating a new one

        Cursor cursor = db.rawQuery("SELECT * FROM " + PacPacMonstersContract.MovesEntry.TABLE_NAME, null);
        try
        {
            TextView displayView = (TextView) findViewById(R.id.text_view_test3);
            displayView.setText("Number of rows in moves database table: " + cursor.getCount());
        }
        finally
        {
            cursor.close();
        }
    }


}
