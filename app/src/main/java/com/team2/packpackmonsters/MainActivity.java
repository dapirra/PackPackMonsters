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

public class MainActivity extends AppCompatActivity implements DialogUserProfile.DialogUserProfileListener
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

    public ArrayList<MonstersInfo> createoneMonster()
    {
        SQLiteDatabase db = MonDbHelper.getReadableDatabase();//Opening the database or creating a new one

        Cursor cursor = db.rawQuery("SELECT * FROM " + PacPacMonstersContract.ItemsEntry.TABLE_NAME, null);

        return null;
    }

    public void openDialog()
    {
        DialogUserProfile dialogUserProfile = new DialogUserProfile();
        dialogUserProfile.show(getSupportFragmentManager(), "Username dialog");
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

    private void displayDatabaseInfoMonster()
    {//TEST TO SEE IF DATABASE WORKS FOR MONSTER...

        SQLiteDatabase db = MonDbHelper.getReadableDatabase();//Opening the database or creating a new one

        Cursor cursor = db.rawQuery("SELECT * FROM " + PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null);
        try
        {
            //TextView displayView = (TextView) findViewById(R.id.text_view_test2);
            //displayView.setText("Number of rows in Monster database table: " + cursor.getCount());
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
                if (dataItem.equalsIgnoreCase(nameItem))
                {

                }
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

    private void insertMonsters()//Monsters are inserted
    {

        SQLiteDatabase db1 = MonDbHelper.getReadableDatabase();//Puts into readable so we can see if a user with the same name exists
        String nameMonster = "Stone";
        String[] projection = {PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE};//String of columns we want retrieved only
        String selection = PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE + "=?";//pinpoints where name = username
        String[] selectionArgs = {nameMonster};//These 2 lines ^^^^^^^^^

        Cursor c = db1.query(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (c.moveToFirst())//This if then statement checks to see if we already have moves inserted
        {
            try
            {
                int nameColumnIndex = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE);//checks if we have the move inserted
                dataMonsterName = c.getString(nameColumnIndex);
                if (dataMonsterName.equalsIgnoreCase(nameMonster))
                {

                }
            } finally
            {
                c.close();
            }

        } else
        {//If we dont have moves inserted then insert the moves


            SQLiteDatabase db = MonDbHelper.getWritableDatabase();//Database is in writemode
            ContentValues values = new ContentValues();//Content Values object Column names are keys and attributes in "" are values


            //Pevero Fire Monster #1
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Pevero");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "16");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "1");//Fire is Type 1
            //Move 1 - Fire Ball - Fire Monster #1
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE, "Fire ball");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_BUFF, "0");
            //Move 2 - Forest Fire - Fire Monster #1
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO, "Forest Fire");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_BUFF, "0");//Buff activated = 1 Not activated = 0
            //Move 3 - Fire Up - Fire Monster #1
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE, "Fire Up");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_DAMAGE, "0");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_BUFF, "1");
            //Move 4 - Smack - Fire Monster #1
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR, "Smack");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_BUFF, "0");
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            long newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);
//*************************************************************************************************************************************

            //Moultin - Fire Monster #2
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Moultin");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "21");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "1");//Fire is Type 1
            //Move 1 - Fire Ball - Fire Monster #2
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE, "Fire Ball");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_BUFF, "0");
            //Move 2 - Arson Blast - Fire Monster #2
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO, "Arson Blast");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_BUFF, "0");
            //Move 3 - Evaporate - Fire Monster #2
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE, "Evaporate");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_DAMAGE, "15");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_BUFF, "1");
            //Move 4 - Yodel - Fire Monster #2
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR, "Yodel");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_BUFF, "0");
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);
//************************************************************************************************************************************


            //Ignaight - Fire Monster #3
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Ignaight");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "19");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "1");//Fire is Type 1
            //Move 1 - Fire Ball
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE, "Fire Ball");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_BUFF, "0");
            //Move 2 - Inferno
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO, "Inferno");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_BUFF, "0");
            //Move 3 - Engulf
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE, "Engulf");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_BUFF, "0");
            //Move 4 - Scratch
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR, "Scratch");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_BUFF, "0");
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);
            //***********************************************************************************************************

            //Ahkwa Water Monster #1
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Ahkwa");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "17");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "2");//Water is Type 2
            //Move 1 - Splash
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE, "Splash");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_BUFF, "0");
            //Move 2 - Tsunami
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO, "Tsunami");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_BUFF, "0");
            //Move 3 - Condensate
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE, "Condensate");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_DAMAGE, "0");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_BUFF, "1");
            //Move 4 - Poke
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR, "Poke");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_BUFF, "0");
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);
//************************************************************************************************************************************

            //Vaypour - Water Monster #2
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Vaypour");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "22");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "2");
            //Move 1 - Splash
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE, "Splash");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_BUFF, "0");
            //Move 2 - Blizzard
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO, "Blizzard");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_BUFF, "0");
            //Move 3 - Freeze
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE, "Freeze");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_DAMAGE, "15");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_BUFF, "1");
            //Move 4 - Kick
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR, "Kick");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_BUFF, "0");//Water is Type 2
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);
//*****************************************************************************************************************************************

            //Aiyce - Water Monster #2
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Aiyce");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "21");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "2");
            //Move 1 - Splash
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE, "Splash");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_BUFF, "0");
            //Move 2 - Icicle Blast
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO, "Icicle Blast");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_BUFF, "0");
            //Move 3 - Flood
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE, "Flood");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_BUFF, "0");
            //Move 4 - Punch
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR, "Punch");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_BUFF, "0");//Water is Type 2
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);
            //***********************************************************************************************************************

            //Whoush Wind Monster #1
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Whoush");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "17");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "3");
            //Move 1 - Gust
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE, "Gust");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_BUFF, "0");
            //Move 2 - Breaking Wind
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO, "Breaking Wind");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_BUFF, "0");
            //Move 3 - Zephyr
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE, "Zephyr");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_DAMAGE, "0");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_BUFF, "1");
            //Move 4 - Flick
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR, "Flick");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_BUFF, "0");//Wind is Type 3
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);
//***************************************************************************************************************************

            //Blough Wind Monster #2
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Blough");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "22");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "3");//Wind is Type 3
            //Move 1 - Gust
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE, "Gust");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_BUFF, "0");
            //Move 2 - Reverse Vacuum
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO, "Reverse Vacuum");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_BUFF, "0");
            //Move 3 - Deflect
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE, "Deflect");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_DAMAGE, "15");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_BUFF, "1");
            //Move 4 - Clap
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR, "Clap");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_BUFF, "0");
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);
//*************************************************************************************************************************************


            //Aerou Wind Monster #3
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Aerou");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "18");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "3");//Wind is Type 3
            //Move 1 - Gust
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE, "Gust");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_BUFF, "0");
            //Move 2 - Tornado
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO, "Tornado");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_BUFF, "0");
            //Move 3 - Suffocate
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE, "Suffocate");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_BUFF, "0");
            //Move 4 - Stab
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR, "Stab");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_BUFF, "0");
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);
//*************************************************************************************************************************************

            //Rakh Earth Monster #1
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Rakh");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "18");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "4");//Earth is type 4
            //Move 1 - Stone
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE, "Stone");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_BUFF, "0");
            //Move 2 - Crush
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO, "Crush");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_BUFF, "0");
            //Move 3 - Harden
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE, "Harden");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_DAMAGE, "0");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_BUFF, "1");
            //Move 4 - Lick
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR, "Lick");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_BUFF, "0");
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);
//**************************************************************************************************************************************


            //Kwayk Earth Monster #2
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Kwayk");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "24");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "4");
            //Move 1 - Stone
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE, "Stone");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_BUFF, "0");
            //Move 2 - Landslide
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO, "Landslide");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_BUFF, "0");
            //Move 3 - Wall Up
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE, "Wall Up");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_DAMAGE, "15");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_BUFF, "1");
            //Move 4 - Tickle
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR, "Tickle");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_BUFF, "0");//Earth is Type 4
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);//Actually inserts
            //the data                  ^^^The name of the table inserted                                       ^^Values object
            //********************************************************************************************************************

            //Dyurt Earth Monster #3
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Dyurt");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "20");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "4");
            //Move 1 - Stone
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE, "Stone");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_BUFF, "0");
            //Move 2 - Bury
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO, "Bury");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_BUFF, "0");
            //Move 3 - Drought
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE, "Drought");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_DAMAGE, "10");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_BUFF, "0");
            //Move 4 - Whip
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR, "Whip");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_DAMAGE, "5");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_BUFF, "0");//Earth is Type 4
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);//Actually inserts
            //the data                  ^^^The name of the table inserted                                       ^^Values object
//*************************************************************************************************************************************
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
    public boolean onCreateOptionsMenu(Menu menu)//Inflates the menu option
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) //User clicked on menu item statistics menu
    {
        switch (item.getItemId())
        {
            case R.id.action_show_stats://Right now for debugging, click on show stats and displays stats but for now, just # of rows
                insertUserProfile();
                displayDatabaseInfoUserProfile();
                //displayStats(); method which will display stats
                return true;
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void applyTexts(String username)//Here we can then set stuff for database. Catch the username in here and set it
    {

        textViewUsername.setText(username);

        SQLiteDatabase db = UsDbHelper.getReadableDatabase();//Puts into readable so we can see if a user with the same name exists

        String[] projection = {PacPacMonstersContract.UserProfileEntry.COLUMN_NAME};//String of columns we want retrieved only
        String selection = PacPacMonstersContract.UserProfileEntry.COLUMN_NAME + "=?";//pinpoints where name = username
        String[] selectionArgs = {username};//These 2 lines ^^^^^^^^^

        Cursor c = db.query(PacPacMonstersContract.UserProfileEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        if (c.moveToFirst())
        {
            try
            {
                int nameColumnIndex = c.getColumnIndex(PacPacMonstersContract.UserProfileEntry.COLUMN_NAME);
                dataUserName = c.getString(nameColumnIndex);
                    //TextView displayView = (TextView) findViewById(R.id.text_view_test);
                    //displayView.setText("Data = " + dataUserName + " username = " + username);
                    if (dataUserName.equalsIgnoreCase(username)) {
                       //TextView displayView2 = (TextView) findViewById(R.id.text_view_test);
                      //  displayView.setText("User found! Data = " + dataUserName + " username = " + username);

                    } else {
                        SQLiteDatabase db2 = UsDbHelper.getWritableDatabase();//Write mode is to CREATE, UPDATE, & DELETE

                        ContentValues values = new ContentValues();//Content Values object Column names are keys and attributes in "" are values
                        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_NAME, username);
                        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_WINS, "0");
                        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_LOSSES, "0");
                        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_SURRENDERS, "0");

                        long newRowId = db2.insert(PacPacMonstersContract.UserProfileEntry.TABLE_NAME, null, values);
                        //TextView displayView2 = (TextView) findViewById(R.id.text_view_test);
                        //displayView.setText("New user created Data = " + dataUserName + " username = " + username);
                    }
            } finally
            {
                c.close();
            }
        }






        /*if(data == username)
            {
                TextView displayView = (TextView) findViewById(R.id.text_view_test);
                displayView.setText("User found! Data = " + data + " username = " + username);

            }
            else
                {
                SQLiteDatabase db2 = MDbHelper.getWritableDatabase();//Write mode is to CREATE, UPDATE, & DELETE

                ContentValues values = new ContentValues();//Content Values object Column names are keys and attributes in "" are values
                values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_NAME, username);
                values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_WINS, "0");
                values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_LOSSES, "0");
                values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_SURRENDERS, "0");

                long newRowId = db2.insert(PacPacMonstersContract.UserProfileEntry.TABLE_NAME, null, values);
                TextView displayView = (TextView) findViewById(R.id.text_view_test);
                displayView.setText("New user created Data = " + data + " username = " + username);
                }*/
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
