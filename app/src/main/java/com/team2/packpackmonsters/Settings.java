package com.team2.packpackmonsters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.team2.packpackmonsters.data.AllMonstersDb;
import com.team2.packpackmonsters.data.PacPacMonstersContract;

import java.util.ArrayList;

/**
 * Created by Owner on 4/27/2018.
 */

public class Settings {

    public static ArrayList<Monster> AllMonsters;

    public static void loadData(Context context) {

        SQLiteDatabase db = (new AllMonstersDb(context)).getReadableDatabase();
        Cursor c = db.query(PacPacMonstersContract.PacPacMonsterEntry.TABLE_NAME, null, null, null, null, null, null);
        while (c.moveToNext()) {
            ArrayList<Move> moves= new ArrayList<>();
            moves.add(new Move("", "", 0, false));
            moves.add(new Move("", "", 0, false));
            moves.add(new Move("", "", 0, false));
            moves.add(new Move("", "", 0, false));

            AllMonsters.add(new Monster(
                    c.getString(1),
                    c.getString(2),
                    c.getInt(3),
                    c.getInt(4),
                    null,
                    moves
            ));
        }
    }
}
