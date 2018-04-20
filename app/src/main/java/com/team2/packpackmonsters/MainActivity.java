package com.team2.packpackmonsters;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.team2.packpackmonsters.data.PacPacMonstersContract;
import com.team2.packpackmonsters.data.UserProfileDbHelper;

public class MainActivity extends AppCompatActivity {

    private UserProfileDbHelper MDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayDatabaseInfo();
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
}
