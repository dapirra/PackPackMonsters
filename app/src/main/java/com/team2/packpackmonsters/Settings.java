package com.team2.packpackmonsters;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.team2.packpackmonsters.data.AllMonstersDb;
import com.team2.packpackmonsters.data.PacPacMonstersContract;

import java.util.ArrayList;

/**
 * Created by Owner on 4/27/2018.
 */

public class Settings {

    public static ArrayList<Monster> allMonsters;
    public static ArrayList<Monster> packDexMonsters;
    public static ArrayList<Monster> playerMonsters;
    public static ArrayList<Monster> opponentMonsters;
    public static SharedPreferences prefs;

    public static final class STATISTICS {
        public static String name;
        public static int wins;
        public static int losses;
        public static int surrenders;
    }

    public static void loadData(Context context) {

        prefs = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        STATISTICS.name = prefs.getString("name", "");
        STATISTICS.wins = prefs.getInt("wins", 0);
        STATISTICS.losses = prefs.getInt("losses", 0);
        STATISTICS.surrenders = prefs.getInt("surrenders", 0);

        SQLiteDatabase db = (new AllMonstersDb(context)).getReadableDatabase();
        Cursor c = db.query(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, null, null, null, null, null);
        allMonsters = new ArrayList<>();
        packDexMonsters = new ArrayList<>();
        ArrayList<Move> moves;

        while (c.moveToNext()) {
            moves = new ArrayList<>();
            moves.add(new Move(c.getString(5), c.getString(6), c.getInt(7), 1));
            moves.add(new Move(c.getString(8), c.getString(9), c.getInt(10), 2));
            moves.add(new Move(c.getString(11), c.getString(12), c.getInt(13), 3));
            moves.add(new Move(c.getString(14), c.getString(15), c.getInt(16), 4));

            Monster m = new Monster(
                    c.getString(1),
                    c.getString(2),
                    c.getInt(3),
                    c.getInt(4),
                    moves
            );

            allMonsters.add(m);
            packDexMonsters.add(m);
        }

        packDexMonsters.add(9, null);
        packDexMonsters.add(6, null);
        packDexMonsters.add(3, null);
        packDexMonsters.add(0, null);

        c.close();
    }

    public static void saveData() {
        prefs.edit().putString("name", STATISTICS.name).apply();
        prefs.edit().putInt("wins", STATISTICS.wins).apply();
        prefs.edit().putInt("losses", STATISTICS.losses).apply();
        prefs.edit().putInt("surrenders", STATISTICS.surrenders).apply();
    }
}
