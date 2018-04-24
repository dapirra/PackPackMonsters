package com.team2.packpackmonsters;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.team2.packpackmonsters.data.ItemsDbHelper;
import com.team2.packpackmonsters.data.MonsterMoveDbHelper;
import com.team2.packpackmonsters.data.MonstersDbHelper;
import com.team2.packpackmonsters.data.MovesDbHelper;
import com.team2.packpackmonsters.data.PacPacMonstersContract;
import com.team2.packpackmonsters.data.UserProfileDbHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements DialogUserProfile.DialogUserProfileListener
{

    private UserProfileDbHelper UsDbHelper;
    private MonstersDbHelper MonDbHelper;
    private MovesDbHelper MovDbHelper;
    private ItemsDbHelper ItDbHelper;
    private TextView textViewUsername;
    static String dataMove;
    static String dataUserName;
    static String dataMonsterName;
    static String dataItem;
    private MonsterMoveDbHelper MonMovDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Button> mainBtns = new ArrayList<>();
        mainBtns.add((Button) findViewById(R.id.main_btn_top_left));
        mainBtns.add((Button) findViewById(R.id.main_btn_top_right));
        mainBtns.add((Button) findViewById(R.id.main_btn_bot_left));
        mainBtns.add((Button) findViewById(R.id.main_btn_bot_right));

        for (Button btn : mainBtns)
        {
            btn.setOnClickListener(new MainBtnOnClickListener());
        }

        MonDbHelper = new MonstersDbHelper(this);
        UsDbHelper = new UserProfileDbHelper(this);
        MovDbHelper = new MovesDbHelper(this);
        ItDbHelper = new ItemsDbHelper(this);
        MonMovDbHelper = new MonsterMoveDbHelper(this);
        insertMoves();
        insertMonsters();
        insertItems();
        //displayDatabaseMonsterMoves();
        displayDatabaseInfoItems();
        displayDatabaseInfoMoves();
        displayDatabaseInfoMonster();

        //UserProfile displaying name activity
        textViewUsername = findViewById(R.id.text_view_username);
        for(int i = 0; i <= 3; i++)
        {
            openDialog();
        }
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
            TextView displayView = (TextView) findViewById(R.id.text_view_test);
            displayView.setText("Number of rows in user profile database table: " + cursor.getCount());
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
            TextView displayView = (TextView) findViewById(R.id.text_view_test2);
            displayView.setText("Number of rows in Monster database table: " + cursor.getCount());
        } finally
        {
            cursor.close();
        }
    }

    private void displayDatabaseInfoMoves()
    {//TEST TO SEE IF DATABASE WORKS FOR MOVES...

        SQLiteDatabase db = MovDbHelper.getReadableDatabase();//Opening the database or creating a new one

        Cursor cursor = db.rawQuery("SELECT * FROM " + PacPacMonstersContract.MovesEntry.TABLE_NAME, null);
        try
        {
            TextView displayView = (TextView) findViewById(R.id.text_view_test3);
            displayView.setText("Number of rows in moves database table: " + cursor.getCount());
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
            TextView displayView = (TextView) findViewById(R.id.text_view_test4);
            displayView.setText("Number of rows in Items database table: " + cursor.getCount());
        } finally
        {
            cursor.close();
        }
    }

    private void displayDatabaseMonsterMoves()//MonsterMoves are inserted
    {
        SQLiteDatabase db = MonMovDbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + PacPacMonstersContract.MonsterMoveEntry.TABLE_NAME, null);
        try
        {
            TextView displayView = (TextView) findViewById(R.id.text_view_test5);
            displayView.setText("Number of rows in MonsterMove database table: " + cursor.getCount());
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
        String nameMonster = "Fire One";
        String[] projection = {PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME};//String of columns we want retrieved only
        String selection = PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME + "=?";//pinpoints where name = username
        String[] selectionArgs = {nameMonster};//These 2 lines ^^^^^^^^^

        Cursor c = db1.query(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (c.moveToFirst())//This if then statement checks to see if we already have moves inserted
        {
            try
            {
                int nameColumnIndex = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME);//checks if we have the move inserted
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


            //Fire Monster #1
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Fire One");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "1");//Fire is Type 1
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            long newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);

            //Fire Monster #2
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Fire Two");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "1");//Fire is Type 1
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);

            //Water Monster #1
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Water One");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "2");//Water is Type 2
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);

            //Water Monster #2
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Water Two");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "2");//Water is Type 2
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);

            //Wind Monster #1
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Wind One");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "3");//Wind is Type 3
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);

            //Wind Monster #2
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Wind Two");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "3");//Wind is Type 3
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);

            //Earth Monster #1
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Earth One");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "4");//Earth is type 4
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);

            //Earth Monster #2
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME, "Earth Two");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP, "50");
            values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE, "4");//Earth is Type 4
            //values.put(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_IMAGE, "");//Need to add images for all
            newRowIdMonster = db.insert(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, values);//Actually inserts
            //the data                  ^^^The name of the table inserted                                       ^^Values object

        }
    }

    private void insertMoves()//Moves for PacPacMonsters
    {
        SQLiteDatabase db1 = MovDbHelper.getReadableDatabase();//Puts into readable so we can see if a user with the same name exists
        String nameMove = "Fire ball";
        String[] projection = {PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME};//String of columns we want retrieved only
        String selection = PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME + "=?";//pinpoints where name = username
        String[] selectionArgs = {nameMove};//These 2 lines ^^^^^^^^^

        Cursor c = db1.query(PacPacMonstersContract.MovesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (c.moveToFirst())//This if then statement checks to see if we already have moves inserted
        {
            try
            {
                int nameColumnIndex = c.getColumnIndex(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME);//checks if we have the move inserted
                dataMove = c.getString(nameColumnIndex);
                if (dataMove.equalsIgnoreCase(nameMove))
                {

                }
            } finally
            {
                c.close();
            }

        } else
        {//If we dont have moves inserted then insert the moves

            SQLiteDatabase db = MovDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();


            //Move 1 Fireball - Fire PacPacMonster #1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Fire ball");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            long newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 2 Forest Fire(Special Attack Move) - Fire PacPacMonster #1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Forest Fire");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 3 Fire-Up (Attack Buff) - Fire PacPacMonster #1 This doesn't do damage, so when we play it it buffs other stats
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Fire-Up");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 4 Smack (Generic Attack) - Fire PacPacMonster #1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Smack");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);


            //For Fire PacPacMonster#2

            //Move 1 -- Fireball -- Already declared

            //Move 5 Arson Blast (Special Attack) - Fire PacPacMonster #2
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Arson Blast");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 6 Evaporate (Defense Buff) - Fire PacPacMonster #2
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Evaporate");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 7 Yodel (Generic Move) - Fire PacPacMonster #2
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Yodel");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //PacPacMonster #3 Wind

            //Move 8 Gust - Wind PacPacMonster#1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Gust");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 9 Breaking Wind (Special Move) - Wind PacPacMonster#1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Breaking Wind");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 10 Wind Up (Attack Buff) - Wind PacPacMonster#1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Wind Up");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 11 Flick (Generic Attack) - Wind PacPacMonster#1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Flick");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //PacPacMonster #4 - Wind

            //Move 8 Gust -- Already defined

            //Move 12 Reverse Vacuum (Special Move) - Wind PacPacMonster#2
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Reverse Vacuum");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);


            //Move 13 Deflect (Defense Buff) - Wind PacPacMonster#2
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Deflect");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);


            //Move 14 Clap (Generic) - Wind PacPacMonster#2
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Clap");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //PacPacMonster #5 - Water

            //Move 15 Splash - Water PacPacMonster#1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Splash");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 16 Tsunami (Special Move) - Water PacPacMonster#1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Tsunami");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 17 Condensate (Attack Buff) - Water PacPacMonster#1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Condensate");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 18 Poke (Generic Attack) - Water PacPacMonster#1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Poke");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //PacPacMonster #6 - Water

            //Move 15 -- Splash -- Already defined

            //Move 19 Flood (Special Move) - Water PacPacMonster#2
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Flood");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 20 Freeze (Defense Buff) - Water PacPacMonster#2
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Splash");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 21 Kick (Generic) - Water PacPacMonster#2
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Kick");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //PacPacMonster #7 - Earth

            //Move 22 Stone - Earth PacPacMonster#1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Stone");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 23 Crush (Special Move) - Earth PacPacMonster#1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Crush");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 24 Harden (Attack Buff) - Earth PacPacMonster#1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Harden");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 25 Lick (Generic) - Earth PacPacMonster#1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Lick");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //PacPacMonster #8 -- Earth

            //Move 22 Stone -- Already defined

            //Move 26 Landslide (Special) - Earth PacPacMonster#2
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Landslide");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 27 Wall Up (Defense Buff) - Earth PacPacMonster#1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Wall Up");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

            //Move 28 Tickle (Generic) - Earth PacPacMonster#1
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Tickle");
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
            values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
            newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);
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
                    TextView displayView = (TextView) findViewById(R.id.text_view_test);
                    displayView.setText("Data = " + dataUserName + " username = " + username);
                    if (dataUserName.equalsIgnoreCase(username)) {
                        TextView displayView2 = (TextView) findViewById(R.id.text_view_test);
                        displayView.setText("User found! Data = " + dataUserName + " username = " + username);

                    } else {
                        SQLiteDatabase db2 = UsDbHelper.getWritableDatabase();//Write mode is to CREATE, UPDATE, & DELETE

                        ContentValues values = new ContentValues();//Content Values object Column names are keys and attributes in "" are values
                        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_NAME, username);
                        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_WINS, "0");
                        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_LOSSES, "0");
                        values.put(PacPacMonstersContract.UserProfileEntry.COLUMN_SURRENDERS, "0");

                        long newRowId = db2.insert(PacPacMonstersContract.UserProfileEntry.TABLE_NAME, null, values);
                        TextView displayView2 = (TextView) findViewById(R.id.text_view_test);
                        displayView.setText("New user created Data = " + dataUserName + " username = " + username);
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
}
