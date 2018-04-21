package com.team2.packpackmonsters;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
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

public class MainActivity extends AppCompatActivity implements DialogUserProfile.DialogUserProfileListener {

    private UserProfileDbHelper MDbHelper;
    private MonstersDbHelper ADbHelper;
    private MovesDbHelper BDbHelper;
    private TextView textViewUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //displayDatabaseInfo();
        //displayDatabaseInfo2();
        //displayDatabaseInfo3();
        ADbHelper = new MonstersDbHelper(this);
        MDbHelper = new UserProfileDbHelper(this);
        BDbHelper = new MovesDbHelper(this);
        insertMoves();
        displayDatabaseInfo3();

        //UserProfile displaying name activity
        textViewUsername = findViewById(R.id.text_view_username);
        openDialog();

    }

    public void openDialog()
    {
        DialogUserProfile dialogUserProfile = new DialogUserProfile();
        dialogUserProfile.show(getSupportFragmentManager(), "Username dialog");
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

    private void insertMoves()//Moves for PacPacMonsters
    {
        SQLiteDatabase db = BDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        //Move 0 Fireball - Fire PacPacMonster #1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Fire ball");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        long newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 1 Forest Fire(Special Attack Move) - Fire PacPacMonster #1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Forest Fire");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 2 Fire-Up (Attack Buff) - Fire PacPacMonster #1 This doesn't do damage, so when we play it it buffs other stats
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Fire-Up");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 3 Smack (Generic Attack) - Fire PacPacMonster #1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Smack");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);


        //For Fire PacPacMonster#2

        //Move 0 -- Fireball -- Already declared

        //Move 4 Arson Blast (Special Attack) - Fire PacPacMonster #2
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Arson Blast");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 5 Evaporate (Defense Buff) - Fire PacPacMonster #2
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Evaporate");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 6 Yodel (Generic Move) - Fire PacPacMonster #2
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Yodel");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //PacPacMonster #3 Wind

        //Move 7 Gust - Wind PacPacMonster#1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Gust");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
    newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 8 Breaking Wind (Special Move) - Wind PacPacMonster#1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Breaking Wind");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 9 Wind Up (Attack Buff) - Wind PacPacMonster#1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Wind Up");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 10 Flick (Generic Attack) - Wind PacPacMonster#1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Flick");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //PacPacMonster #4 - Wind

        //Move 7 Gust -- Already defined

        //Move 11 Reverse Vacuum (Special Move) - Wind PacPacMonster#2
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Reverse Vacuum");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);


        //Move 12 Deflect (Defense Buff) - Wind PacPacMonster#2
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Deflect");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);


        //Move 13 Clap (Generic) - Wind PacPacMonster#2
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Clap");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //PacPacMonster #5 - Water

        //Move 14 Splash - Water PacPacMonster#1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Splash");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 15 Tsunami (Special Move) - Water PacPacMonster#1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Tsunami");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 16 Condensate (Attack Buff) - Water PacPacMonster#1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Condensate");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 17 Poke (Generic Attack) - Water PacPacMonster#1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Poke");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //PacPacMonster #6 - Water

        //Move 14 -- Splash -- Already defined

        //Move 18 Flood (Special Move) - Water PacPacMonster#2
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Flood");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 19 Freeze (Defense Buff) - Water PacPacMonster#2
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Splash");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 20 Kick (Generic) - Water PacPacMonster#2
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Kick");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //PacPacMonster #7 - Earth

        //Move 21 Stone - Earth PacPacMonster#1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Stone");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 22 Crush (Special Move) - Earth PacPacMonster#1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Crush");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 23 Harden (Attack Buff) - Earth PacPacMonster#1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Harden");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 24 Lick (Generic) - Earth PacPacMonster#1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Lick");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //PacPacMonster #8 -- Earth

        //Move 21 Stone -- Already defined

        //Move 25 Landslide (Special) - Earth PacPacMonster#2
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Landslide");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 26 Wall Up (Defense Buff) - Earth PacPacMonster#1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Wall Up");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

        //Move 27 Tickle (Generic) - Earth PacPacMonster#1
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_NAME, "Tickle");
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_MOVE_DAMAGE, "0");//How much damage it does
        values.put(PacPacMonstersContract.MovesEntry.COLUMN_IS_MOVE_BUFF, "0");//When facing a weaker pac pac monster
        newRowIdMoves = db.insert(PacPacMonstersContract.MovesEntry.TABLE_NAME, null, values);

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

    @Override
    public void applyTexts(String username)//Here we can then set stuff for database. Catch the username in here and set it
    {
        textViewUsername.setText(username);

    }


}
