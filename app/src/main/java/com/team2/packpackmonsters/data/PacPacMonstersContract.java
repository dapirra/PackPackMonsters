package com.team2.packpackmonsters.data;

import android.provider.BaseColumns;

/**
 * Created by Owner on 4/20/2018.
 */

public final class PacPacMonstersContract//Contract class, allows easier way to read the code
{

    private PacPacMonstersContract()//Prevents any unintentional iterations
    {

    }

    public static final class UserProfileEntry implements BaseColumns//Data table for User Profile
    {
        public final static String TABLE_NAME = "UserProfile";//Name of the table
        public final static String _ID = BaseColumns._ID;//ID column for table
        public final static String COLUMN_NAME = "name";//Name of user
        public final static String COLUMN_WINS = "wins";//Amount of wins for user
        public final static String COLUMN_LOSSES = "loss";//Losses
        public final static String COLUMN_SURRENDERS = "surrender";//Surrenders

    }

    public static final class PacPacMonsterEntry implements BaseColumns//Data table for PAC PAC Monsters
    {
        public final static String TABLE_NAME = "Monsters";//Name of the table
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";//Name of monster
        public final static String COLUMN_HP = "hp";//Health for monster
        public final static String COLUMN_TYPE = "type";
        public final static String COLUMN_MOVE_ONE = "move_one";
        public final static String COLUMN_MOVE_ONE_DAMAGE = "move_one_damage";
        public final static String COLUMN_MOVE_ONE_BUFF = "move_one_buff";
        public final static String COLUMN_MOVE_TWO = "move_two";
        public final static String COLUMN_MOVE_TWO_DAMAGE = "move_two_damage";
        public final static String COLUMN_MOVE_TWO_BUFF = "move_two_buff";
        public final static String COLUMN_MOVE_THREE = "move_three";
        public final static String COLUMN_MOVE_THREE_DAMAGE = "move_three_damage";
        public final static String COLUMN_MOVE_THREE_BUFF = "move_three_buff";
        public final static String COLUMN_MOVE_FOUR = "move_four";
        public final static String COLUMN_MOVE_FOUR_DAMAGE = "move_four_damage";
        public final static String COLUMN_MOVE_FOUR_BUFF = "move_four_buff";

        public final static int FIRE = 1;
        public final static int WATER = 2;
        public final static int WIND = 3;
        public final static int EARTH = 4;

        //Type for monster, grass, water, etc. Might make this an integer where 1 = water 2 = fire etc.
//        public final static String COLUMN_IMAGE = "image";//Picture of monster, might need to remove...
    }


    public static final class ItemsEntry implements BaseColumns//Data table for items, Do we have to add to it????
    {
        public final static String TABLE_NAME = "Items";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_ITEM_NAME = "name";
    }
}
