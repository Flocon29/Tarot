package flo.tarot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Flo on 04/05/2016.
 */
public class GameData {
    protected final static int VERSION = 1;
    protected final static String NOM_BDD = "tarot.db";
    public static final String TAROT_TABLE_NAME = "tarot";
    public static final String TAROT_KEY = "id";
    private static final int NUM_KEY = 0;
    public static final String TAROT_PLAYERS= "players";
    private static final int NUM_PLAYER = 1;
    public static final String TAROT_POINTS = "points";
    private static final int NUM_POINTS = 2;

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
        values.put(TAROT_POINTS, g.getScoring().toString());
        return mDb.update(this.TAROT_TABLE_NAME, values, this.TAROT_KEY + " = " + g.getId(), null);
    }

    public void newData(Game g) {
        ContentValues values = new ContentValues();
        values.put(this.TAROT_PLAYERS, g.getJoueurs().toString());
        values.put(this.TAROT_POINTS, g.getScoring().toString());
        mDb.insert(this.TAROT_TABLE_NAME, null, values);
        g.setId(giveId());
    }

    public boolean removeGame(int id) {
        mDb.delete(TAROT_TABLE_NAME, TAROT_KEY + " = " + id, null);
        return true;
    }

    public Game[] getAll(){
        Cursor c = mDb.query(TAROT_TABLE_NAME, new String[]{TAROT_KEY, TAROT_PLAYERS, TAROT_POINTS},null,null,null,null,null,null);
        Game[] gamesAll = new Game[c.getCount()];
        c.moveToFirst();
        for (int i = 0; i<c.getCount(); i++){
            gamesAll[i] = new Game();
            gamesAll[i].setId(c.getInt(NUM_KEY));
            String joueurs = c.getString(NUM_PLAYER);
            joueurs = joueurs.replace("[", "");
            joueurs = joueurs.replace("]", "");
            joueurs = joueurs.replace(" ", "");
            String[] splittedPlayers = joueurs.split(",");
            ArrayList players = new ArrayList();
            for(int k = 0; k<splittedPlayers.length;k++){
                players.add(splittedPlayers[k]);
            }
            gamesAll[i].setJoueurs(players);
            String scoring = c.getString(NUM_POINTS);
            scoring = scoring.replace("[", "");
            scoring = scoring.replace("]", "");
            scoring = scoring.replace(" ", "");
            String[] splittedScoring = scoring.split(",");
            ArrayList scores = new ArrayList();
            for(int j=0;j<splittedScoring.length;j++){
                scores.add(splittedScoring[j]);
                //System.out.println("TEST :: Unité = " + splitted[j]);
            }
            //System.out.println("TEST :: scoring = " + scoring);
            gamesAll[i].setScoring(scores);
            c.moveToNext();
        }
        return gamesAll;
    }

    public int getSaves(){
        Cursor c = mDb.query(TAROT_TABLE_NAME, new String[]{TAROT_KEY}, null, null, null, null, null);
        return c.getCount();
    }

    public int giveId(){
        Cursor c = mDb.query(TAROT_TABLE_NAME, new String[]{TAROT_KEY},null,null,null,null,null,null);
        c.moveToLast();
        return c.getInt(NUM_KEY);
    }
}
