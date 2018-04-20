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
        public final static String COLUMN_FAV_MONSTER = "favorite monster";//Favorite monster they used constantly
        public final static String COLUMN_SURRENDERS = "surrender";//Surrenders

    }

    public static final class PacPacMonsterEntry implements BaseColumns//Data table for PAC PAC Monsters
    {
        public final static String TABLE_NAME = "Monsters";//Name of the table
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "name";//Name of monster
        public final static String COLUMN_HP = "hp";//Health for monster
        public final static String COLUMN_TYPE = "type";//Type for monster, grass, water, etc. Might make this an integer where 1 = water 2 = fire etc.
        public final static String COLUMN_IMAGE = "image";//Picture of monster, might need to remove...
    }

    public static final class MovesEntry implements BaseColumns//Data Table for PAC PAC Monsters moves
    {
        public final static String TABLE_NAME = "Moves";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_MOVE_NAME = "MoveName";
        public final static String COLUMN_MOVE_DAMAGE = "MoveDamage";
        public final static String COLUMN_IS_MOVE_BUFF = "IsMoveBuff";
    }

}
