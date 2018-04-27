package com.team2.packpackmonsters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.team2.packpackmonsters.data.MonstersDbHelper;
import com.team2.packpackmonsters.data.PacPacMonstersContract;

import java.util.ArrayList;

/**
 * Created by Owner on 4/24/2018.
 */

public class MonstersInfo
{
    private MonstersDbHelper MonDbHelper;
    private int monsterOneId;
    private String monsterOneName;
    private int monsterOneHp;
    private int monsterOneType;
    private String monsterOneMoveOne;
    private int monsterOneMoveOneDamage;
    private int monsterOneMoveOneBuff;
    private String monsterOneMoveTwo;
    private int monsterOneMoveTwoDamage;
    private int monsterOneMoveTwoBuff;
    private String monsterOneMoveThree;
    private int monsterOneMoveThreeDamage;
    private int monsterOneMoveThreeBuff;
    private String monsterOneMoveFour;
    private int monsterOneMoveFourDamage;
    private int monsterOneMoveFourBuff;
    private String dataMonsterName;



    public MonstersInfo()
    {


    }


    public void createMonsterOne()
    {
        String IdTest = "2";//This will have to be a random number generator, so everytime we call createMonster its random

        SQLiteDatabase db1 = MonDbHelper.getReadableDatabase();
        String[] projection = {PacPacMonstersContract.PacPacMonsterEntry._ID,  //All the columns we want
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME,
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP,
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE,
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE,
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_DAMAGE,
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_BUFF,
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO,
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_DAMAGE,
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_BUFF,
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE,
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_DAMAGE,
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_BUFF,
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR,
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_DAMAGE,
                PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_BUFF};

        String selection = PacPacMonstersContract.PacPacMonsterEntry._ID + "=?"; //We are looking for the ID of the column 1- 12

        String[] selectionArgs = {IdTest};//Purely for testing right now, we will need a random number generator in the future to select any monster

        Cursor c = db1.query(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

            try
            {
                int monOne_ID_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry._ID);
                int monOne_Name_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_NAME);
                int monOne_HP_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_HP);
                int monOne_Type_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_TYPE);
                int monOne_MOne_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE);
                int monOne_MOneDamage_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_DAMAGE);
                int monOne_MOneBuff_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_ONE_BUFF);
                int monOne_MTwo_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO);
                int monOne_MTwoDamage_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_DAMAGE);
                int monOne_MTwoBuff_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_TWO_BUFF);
                int monOne_MThree_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE);
                int monOne_MThreeDamage_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_DAMAGE);
                int monOne_MThreeBuff_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_THREE_BUFF);
                int monOne_MFour_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR);
                int monOne_MFourDamage_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_DAMAGE);
                int monOne_MFourBuff_CI = c.getColumnIndex(PacPacMonstersContract.PacPacMonsterEntry.COLUMN_MOVE_FOUR_BUFF);

            while(c.moveToNext())//Goes through all iterations
            {
                monsterOneId = c.getInt(monOne_ID_CI);
                monsterOneName = c.getString(monOne_Name_CI);
                monsterOneHp = c.getInt(monOne_HP_CI);
                monsterOneType = c.getInt(monOne_Type_CI);
                monsterOneMoveOne = c.getString(monOne_MOne_CI);
                monsterOneMoveOneDamage = c.getInt(monOne_MOneDamage_CI);
                monsterOneMoveOneBuff = c.getInt(monOne_MOneBuff_CI);
                monsterOneMoveTwo = c.getString(monOne_MTwo_CI);
                monsterOneMoveTwoDamage = c.getInt(monOne_MTwoDamage_CI);
                monsterOneMoveTwoBuff = c.getInt(monOne_MTwoBuff_CI);
                monsterOneMoveThree = c.getString(monOne_MThree_CI);
                monsterOneMoveThreeDamage = c.getInt(monOne_MThreeDamage_CI);
                monsterOneMoveThreeBuff = c.getInt(monOne_MThreeBuff_CI);
                monsterOneMoveFour = c.getString(monOne_MFour_CI);
                monsterOneMoveFourDamage = c.getInt(monOne_MFourDamage_CI);
                monsterOneMoveFourBuff = c.getInt(monOne_MFourBuff_CI);

            }


            }

            finally
            {
                c.close();
            }

        System.out.println(monsterOneName + " " + monsterOneMoveFourDamage);
    }
                                                                    //This is passed to a method that wants the Id. Now with this Id it can then call the other methods, getMonsterMoves, get MonsterName
                                                                 //and use this Id to get the specific items it calls for

    /*public ArrayList<MonstersInfo> createMonsterOne()
    {
        SQLiteDatabase db = MonDbHelper.getReadableDatabase();//Opening the database or creating a new one

        Cursor cursor = db.rawQuery("SELECT * FROM " + PacPacMonstersContract.ItemsEntry.TABLE_NAME, null);
        try
        {

        }
    }*/

    public int getMonsterIdOne()
    {
       return monsterOneId;
    }

    public void setMonsterIdOne(int monsterOneId)
    {
        this.monsterOneId = monsterOneId;
    }

    public String getMonsterOneName()
    {
        return monsterOneName;
    }

    public void setMonsterOneName(String monsterOneName)
    {
        this.monsterOneName = monsterOneName;
    }

    public int getMonsterHp()
    {
        return monsterOneHp;
    }

    public void setMonsterHp(int monsterOneHp)
    {
        this.monsterOneHp = monsterOneHp;
    }

    public int getMonsterType()
    {
        return monsterOneType;
    }

    public void setMonsterType(int monsterOneType)
    {
        this.monsterOneType = monsterOneType;
    }

    public String getMonsterMoveOneName()
    {
        return monsterOneMoveOne;
    }

    public void setMonsterMoveOneName(String monsterOneMoveOne)
    {
        this.monsterOneMoveOne = monsterOneMoveOne;
    }

    public int getMonsterMoveOneDamage()
    {
        return monsterOneMoveOneDamage;
    }

    public void setMonsterMoveOneDamage(int monsterOneMoveOneDamage)
    {
        this.monsterOneMoveOneDamage = monsterOneMoveOneDamage;
    }

    public int getMonsterOneBuff()
    {
        return monsterOneMoveOneBuff;
    }

    public void setMonsterOneMoveBuff(int monsterOneMoveOneBuff)
    {
        this.monsterOneMoveOneBuff = monsterOneMoveOneBuff;
    }

    public String getMonsterOneMoveTwo()
    {
        return monsterOneMoveTwo;
    }

    public void setMonsterOneMoveTwo(String monsterOneMoveTwo)
    {
        this.monsterOneMoveTwo = monsterOneMoveTwo;
    }

    public int getMonsterOneMoveTwoDamage()
    {
        return monsterOneMoveTwoDamage;
    }

    public void setMonsterOneMoveTwoDamage(int monsterOneMoveTwoDamage)
    {
        this.monsterOneMoveTwoDamage = monsterOneMoveTwoDamage;
    }

    public int getMonsterOneMOveTwoBuff()
    {
        return monsterOneMoveTwoBuff;
    }

    public void setMonsterOneMoveTwoBuff(int monsterOneMoveTwoBuff)
    {
        this.monsterOneMoveTwoBuff = monsterOneMoveTwoBuff;
    }

    public String getMonsterOneMoveThree()
    {
        return monsterOneMoveThree;
    }

    public void setMonsterOneMoveThree(String monsterOneMoveThree)
    {
        this.monsterOneMoveThree = monsterOneMoveThree;
    }

    public int getMonsterOneMoveThreeDamage()
    {
        return monsterOneMoveThreeDamage;
    }

    public void setMonsterOneMoveThreeDamage(int monsterOneMoveThreeDamage)
    {
        this.monsterOneMoveThreeDamage = monsterOneMoveThreeDamage;
    }

    public int getMonsterOneMoveThreeBuff()
    {
        return monsterOneMoveThreeBuff;
    }

    public void setMonsterOneMoveThreeBuff(int monsterOneMoveThreeBuff)
    {
        this.monsterOneMoveThreeBuff = monsterOneMoveThreeBuff;
    }

    public String getMonsterOneMoveFour()
    {
        return monsterOneMoveFour;
    }

    public void setMonsterOneMoveFour(String monsterOneMoveFour)
    {
        this.monsterOneMoveFour = monsterOneMoveFour;
    }

    public int getMonsterOneMoveFourDamage()
    {
        return monsterOneMoveFourDamage;
    }

    public void setMonsterOneMoveFourDamage(int monsterOneMoveFourDamage)
    {
        this.monsterOneMoveFourDamage = monsterOneMoveFourDamage;
    }

    public int getMonsterOneMoveFourBuff()
    {
        return monsterOneMoveFourBuff;
    }

    public void setMonsterOneMoveFourBuff(int monsterOneMoveFourBuff)
    {
        this.monsterOneMoveFourBuff = monsterOneMoveFourBuff;
    }











}


