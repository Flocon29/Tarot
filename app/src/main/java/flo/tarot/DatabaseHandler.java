package flo.tarot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Flo on 20/01/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    //public static final String GAME_BULLSHIT = "bullshit";   //Variable leurre à ne pas regarder
    public static final String GAME_KEY = "id";
    public static final String GAME_PLAYERS = "players";
    public static final String GAME_POINTS = "points";

    public static final String GAME_TABLE_NAME = "tarot";
    public static final String GAME_TABLE_CREATE =
            "CREATE TABLE " + GAME_TABLE_NAME + " (" +
                    GAME_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GAME_PLAYERS + " TEXT, " +
                    GAME_POINTS + " TEXT);";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GAME_TABLE_CREATE);
    }

    public static final String TAROT_TABLE_DROP = "DROP TABLE IF EXISTS " + GAME_TABLE_NAME + ";";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TAROT_TABLE_DROP);
        onCreate(db);
    }
}
