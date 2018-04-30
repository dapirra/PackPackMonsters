package com.team2.packpackmonsters;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.team2.packpackmonsters.data.ItemsDbHelper;
import com.team2.packpackmonsters.data.PacPacMonstersContract;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemsActivity extends AppCompatActivity {

    ArrayList<TextView> txtsName;
    ArrayList<TextView> txtsDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        setTitle(R.string.items_activity_title);

        //TODO Use both of these to initialize item names/descriptions.
        txtsName = new ArrayList<>();
        txtsName.add((TextView) findViewById(R.id.items_txt_top_item_name));
        txtsName.add((TextView) findViewById(R.id.items_txt_mid_item_name));
        txtsName.add((TextView) findViewById(R.id.items_txt_bot_item_name));

        txtsDescription = new ArrayList<>();
        txtsDescription.add((TextView) findViewById(R.id.items_txt_top_item_description));
        txtsDescription.add((TextView) findViewById(R.id.items_txt_mid_item_description));
        txtsDescription.add((TextView) findViewById(R.id.items_txt_bot_item_description));
    }
}
