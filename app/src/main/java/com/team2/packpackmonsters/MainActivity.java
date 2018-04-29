package com.team2.packpackmonsters;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.team2.packpackmonsters.data.ItemsDbHelper;
import com.team2.packpackmonsters.data.MonstersDbHelper;
import com.team2.packpackmonsters.data.PacPacMonstersContract;
import com.team2.packpackmonsters.data.UserProfileDbHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    private UserProfileDbHelper UsDbHelper;
    private MonstersDbHelper MonDbHelper;
    private ItemsDbHelper ItDbHelper;
    private TextView textViewUsername;
    static String dataUserName;
    static String dataMonsterName;
    static String dataItem;
    private MonstersInfo MonsterOne;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.main_dwr);

        initializeToolbar();
        initializeListeners();
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.main_dwr);

        initializeToolbar();
        initializeListeners();


        //UsDbHelper = new UserProfileDbHelper(this);
        ItDbHelper = new ItemsDbHelper(this);

        insertItems();
        displayDatabaseInfoItems();
        //displayDatabaseInfoMonster();
        MonstersInfo MonsterTwo;

        MonsterTwo = new MonstersInfo();//Monster object in MonsterInfo class
        //MonsterTwo.insertMonsters();
//        MonsterTwo.createMonsterOne();
        //String s1 = MonsterTwo.getMonsterOneName();
        //System.out.println(s1);
        //MonsterOne.createMonsterOne();//Might be able to create one method for each monster, like createMonster, setMonsterName etc..

    }*/

    private void initializeListeners()
    {
        ArrayList<Button> mainBtns = new ArrayList<>();
        mainBtns.add((Button) findViewById(R.id.main_btn_top_left));
        mainBtns.add((Button) findViewById(R.id.main_btn_top_right));
        mainBtns.add((Button) findViewById(R.id.main_btn_bot_left));
        mainBtns.add((Button) findViewById(R.id.main_btn_bot_right));

        for (Button btn : mainBtns)
        {
            btn.setOnClickListener(new MainBtnOnClickListener());
        }
    }

    private void displayDatabaseInfoUserProfile()
    {//TEST TO SEE IF DATABASE WORKS...

        SQLiteDatabase db = UsDbHelper.getReadableDatabase();//Opening the database or creating a new one

        Cursor cursor = db.rawQuery("SELECT * FROM " + PacPacMonstersContract.UserProfileEntry.TABLE_NAME, null);
        try
        {
            //TextView displayView = (TextView) findViewById(R.id.text_view_test);
           // displayView.setText("Number of rows in user profile database table: " + cursor.getCount());
        } finally
        {
            cursor.close();
        }
    }

    private void displayDatabaseInfoItems()
    {//TEST TO SEE IF DATABASE WORKS FOR MOVES...

        SQLiteDatabase db = ItDbHelper.getReadableDatabase();//Opening the database or creating a new one

        Cursor cursor = db.rawQuery("SELECT * FROM " + PacPacMonstersContract.ItemsEntry.TABLE_NAME, null);
        try
        {
            //TextView displayView = (TextView) findViewById(R.id.text_view_test4);
            //displayView.setText("Number of rows in Items database table: " + cursor.getCount());
        } finally
        {
            cursor.close();
        }
    }


    private void insertItems()//Items are inserted
    {
        SQLiteDatabase db1 = ItDbHelper.getReadableDatabase();

        String nameItem = "Revive";
        String[] projection = {PacPacMonstersContract.ItemsEntry.COLUMN_ITEM_NAME};//String of columns we want retrieved only
        String selection = PacPacMonstersContract.ItemsEntry.COLUMN_ITEM_NAME + "=?";//pinpoints where name = username
        String[] selectionArgs = {nameItem};//These 2 lines ^^^^^^^^^

        Cursor c = db1.query(PacPacMonstersContract.ItemsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (c.moveToFirst())//This if then statement checks to see if we already have moves inserted
        {
            try
            {
                int nameColumnIndex = c.getColumnIndex(PacPacMonstersContract.ItemsEntry.COLUMN_ITEM_NAME);//checks if we have the move inserted
                dataItem = c.getString(nameColumnIndex);
            } finally
            {
                c.close();
            }

        } else
        {//If we dont have moves inserted then insert the moves

            SQLiteDatabase db = ItDbHelper.getWritableDatabase();//Database is in writemode
            ContentValues values = new ContentValues();//Content Values object Column names are keys and attributes in "" are values


            //Item 1 - Revive
            values.put(PacPacMonstersContract.ItemsEntry.COLUMN_ITEM_NAME, "Revive");//So when we do battles we will do an if then
            //statement that says if this is the item - then it revives the monster
            long newRowIdItems = db.insert(PacPacMonstersContract.ItemsEntry.TABLE_NAME, null, values);

            //Item 2 - Buff
            values.put(PacPacMonstersContract.ItemsEntry.COLUMN_ITEM_NAME, "Buff");
            newRowIdItems = db.insert(PacPacMonstersContract.ItemsEntry.TABLE_NAME, null, values);

            //Item 3 - Heal
            values.put(PacPacMonstersContract.ItemsEntry.COLUMN_ITEM_NAME, "Heal");
            newRowIdItems = db.insert(PacPacMonstersContract.ItemsEntry.TABLE_NAME, null, values);
        }
    }

    private void insertUserProfile()//Dummy for now to see if this works, inserts stats /// Use to hardcode for Monsters
    {
        // Gets the database in write mode
        SQLiteDatabase db = UsDbHelper.getWritableDatabase();//Write mode is to CREATE, UPDATE, & DELETE


        ContentValues values = new ContentValues();//Content Values object Column names are keys and attributes in "" are values
        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_NAME, "John Snow");
        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_WINS, "5");
        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_LOSSES, "2");
        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_SURRENDERS, "3");

        long newRowId = db.insert(PacPacMonstersContract.UserProfileEntry.TABLE_NAME, null, values);//Actually inserts
        //the data                  ^^^The name of the table inserted                                       ^^Values object
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) //User clicked on menu item statistics menu
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void initializeToolbar()
    {
        Toolbar toolbar = findViewById(R.id.main_tbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private class MainBtnOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.main_btn_top_left:
                    startActivity(new Intent(v.getContext(), BattleActivity.class));
                    break;
                case R.id.main_btn_top_right:
                    startActivity(new Intent(v.getContext(), ItemsActivity.class));
                    break;
                case R.id.main_btn_bot_left:
                    startActivity(new Intent(v.getContext(), PackPackActivity.class));
                    break;
                case R.id.main_btn_bot_right:
                    startActivity(new Intent(v.getContext(), HelpActivity.class));
                    break;
            }
        }
    }

    @Override
    public void onBackPressed()
    {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }
    }
}
