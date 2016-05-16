package flo.tarot;

import android.content.ContentValues;

import java.util.ArrayList;

/**
 * Created by Flo on 09/05/2016.
 */
public class Game {
    private ArrayList joueurs = new ArrayList();
    private ArrayList scoring = new ArrayList();
    private int id;

    public ArrayList getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(ArrayList joueurs) {
        this.joueurs = joueurs;
    }

    public ArrayList getScoring() {
        return scoring;
    }

    public void setScoring(ArrayList scoring) {
        this.scoring = scoring;
    }

    public void addJoueurs(String joueur) {
        this.joueurs.add(joueur);
    }

    public void addScore(String score) {
        this.scoring.add(score);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
