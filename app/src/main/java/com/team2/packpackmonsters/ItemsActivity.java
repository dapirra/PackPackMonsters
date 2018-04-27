package com.team2.packpackmonsters;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.team2.packpackmonsters.data.ItemsDbHelper;
import com.team2.packpackmonsters.data.PacPacMonstersContract;

public class ItemsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        setTitle(getResources().getString(R.string.items_activity_title));
    }
}
