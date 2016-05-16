package flo.tarot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Flo on 20/01/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static final String GAME_BULLSHIT = "bullshit";   //Variable leurre à ne pas regarder
    public static final String GAME_KEY = "id";
    public static final String GAME_PLAYER1 = "player1";
    public static final String GAME_PLAYER2 = "player2";
    public static final String GAME_PLAYER3 = "player3";
    public static final String GAME_PLAYER4 = "player4";
    public static final String GAME_PLAYER5 = "player5";
    public static final String GAME_POINTS_P1 = "pointsP1";
    public static final String GAME_POINTS_P2 = "pointsP2";
    public static final String GAME_POINTS_P3 = "pointsP3";
    public static final String GAME_POINTS_P4 = "pointsP4";
    public static final String GAME_POINTS_P5 = "pointsP5";

    public static final String GAME_TABLE_NAME = "tarot";
    public static final String GAME_TABLE_CREATE =
            "CREATE TABLE " + GAME_TABLE_NAME + " (" +
                    GAME_BULLSHIT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GAME_KEY + " INTEGER, " +
                    GAME_PLAYER1 + " TEXT, " +
                    GAME_PLAYER2 + " TEXT, " +
                    GAME_PLAYER3 + " TEXT, " +
                    GAME_PLAYER4 + " TEXT, " +
                    GAME_PLAYER5 + " TEXT, " +
                    GAME_POINTS_P1 + " REAL, " +
                    GAME_POINTS_P2 + " REAL, " +
                    GAME_POINTS_P3 + " REAL, " +
                    GAME_POINTS_P4 + " REAL, " +
                    GAME_POINTS_P5 + " REAL);";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GAME_TABLE_CREATE);
    }

    public static final String TICTACTOE_TABLE_DROP = "DROP TABLE IF EXISTS " + GAME_TABLE_NAME + ";";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TICTACTOE_TABLE_DROP);
        onCreate(db);
    }
}
