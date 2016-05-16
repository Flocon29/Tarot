package flo.tarot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Flo on 04/05/2016.
 */
public class GameData {
    protected final static int VERSION = 1;
    protected final static String NOM_BDD = "tarot.db";
    public static final String TAROT_TABLE_NAME = "tictactoe";
    public static final String TAROT_KEY = "id";
    private static final int NUM_KEY = 0;
    public static final String TAROT_PLAYERS= "player";
    private static final int NUM_PLAYER = 1;
    public static final String TAROT_POINTS_P1= "pointsP1";
    private static final int NUM_POINTS_P1 = 6;
    public static final String TAROT_POINTS_P2= "pointsP2";
    private static final int NUM_POINTS_P2 = 7;
    public static final String TAROT_POINTS_P3= "pointsP3";
    private static final int NUM_POINTS_P3 = 8;
    public static final String TAROT_POINTS_P4= "pointsP4";
    private static final int NUM_POINTS_P4 = 9;
    public static final String TAROT_POINTS_P5= "pointsP5";
    private static final int NUM_POINTS_P5 = 10;
    public static final String TICTACTOE_TOUR = "tour";
    private static final int NUM_TOUR = 11;

    protected SQLiteDatabase mDb;
    protected DatabaseHandler mHandler;

    public GameData(Context pContext) {
        mHandler = new DatabaseHandler(pContext,NOM_BDD,null,VERSION);
    }

    public void open(){
        //on ouvre la BDD en écriture
        mDb = mHandler.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        mDb.close();
    }

    public SQLiteDatabase getBDD(){
        return mDb;
    }

    public int updateGame(Game g) {
        ContentValues values = new ContentValues();
        for(int i = g.getJoueurs().size()+1; i<g.getScoring().size(); i++){
            values.put(TICTACTOE_TOUR, g.getScoring().get(i).toString());
            i++;
            values.put(TAROT_POINTS_P1, g.getScoring().get(i).toString());
            i++;
            values.put(TAROT_POINTS_P2, g.getScoring().get(i).toString());
            i++;
            values.put(TAROT_POINTS_P3, g.getScoring().get(i).toString());
            i++;
            values.put(TAROT_POINTS_P4, g.getScoring().get(i).toString());
            i++;
            values.put(TAROT_POINTS_P5, g.getScoring().get(i).toString());
        }
        return mDb.update(TAROT_TABLE_NAME, values, TAROT_KEY + " = " + g.getId(), null);
    }

    public long newData(Game g) {
        ContentValues values = new ContentValues();
        values.put(this.TAROT_KEY, g.getId());
        values.put(this.TAROT_PLAYERS, g.getJoueurs().toString());
        for(int i = g.getJoueurs().size()+1; i<g.getScoring().size(); i++){
            values.put(TICTACTOE_TOUR, g.getScoring().get(i).toString());
            i++;
            values.put(TAROT_POINTS_P1, g.getScoring().get(i).toString());
            i++;
            values.put(TAROT_POINTS_P2, g.getScoring().get(i).toString());
            i++;
            values.put(TAROT_POINTS_P3, g.getScoring().get(i).toString());
            i++;
            values.put(TAROT_POINTS_P4, g.getScoring().get(i).toString());
            i++;
            values.put(TAROT_POINTS_P5, g.getScoring().get(i).toString());
        }
        return mDb.insert(this.TAROT_TABLE_NAME, null, values);
    }

    public boolean removeGame(int id) {
        mDb.delete(TAROT_TABLE_NAME, TAROT_KEY + " = " + id, null);
        return true;
    }
}
