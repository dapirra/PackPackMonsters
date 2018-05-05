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

    public static void loadData(Context context) {

        prefs = context.getSharedPreferences("app", Context.MODE_PRIVATE);

        AllMonstersDb monstersDb = new AllMonstersDb(context);
        monstersDb.setForcedUpgrade();
        SQLiteDatabase db = monstersDb.getReadableDatabase();
        Cursor c = db.query(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, null, null, null, null, null);
        allMonsters = new ArrayList<>();
        packDexMonsters = new ArrayList<>();
        ArrayList<Move> moves;

        while (c.moveToNext()) {
            moves = new ArrayList<>();
            moves.add(new Move(c.getString(5), c.getString(6), c.getInt(7), c.getInt(8) == 1, false));
            moves.add(new Move(c.getString(9), c.getString(10), c.getInt(11), c.getInt(12) == 1, false));
            moves.add(new Move(c.getString(13), c.getString(14), c.getInt(15), c.getInt(16) == 1, false));
            moves.add(new Move(c.getString(17), c.getString(18), c.getInt(19), c.getInt(20) == 1, true));

            Monster m = new Monster(
                    c.getString(1),
                    c.getString(2),
                    c.getInt(3),
                    c.getInt(4),
                    null,
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

    public static String loadName() {
        return prefs.getString("name", null);
    }

    public static void saveName(String name) {
        prefs.edit().putString("name", name).apply();
    }
}
