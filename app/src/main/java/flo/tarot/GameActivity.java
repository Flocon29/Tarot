package flo.tarot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity {
    protected final static String JS = "joueurs";
    private int CODE_REQUEST = 1111;
    private GridView grid;
    private ArrayList scores;
    private int tour = 0;
    private final GameData dataGame = new GameData(this);
    private ArrayAdapter<String> arrayAdapter;
    private Game g = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        Intent i = getIntent();
        grid = (GridView) findViewById(R.id.scoreView);

        dataGame.open();

        g.addScore("Tour");

        if(!MainActivity.load){
            if(i.getStringExtra(NewGameActivity.J1).length()>= 1){
                g.addJoueurs(i.getStringExtra(NewGameActivity.J1));
                g.addScore(i.getStringExtra(NewGameActivity.J1));
            }
            else{
                g.addJoueurs("Joueur 1");
                g.addScore("Joueur 1");
            }
            if(i.getStringExtra(NewGameActivity.J2).length()>=1){
                g.addJoueurs(i.getStringExtra(NewGameActivity.J2));
                g.addScore(i.getStringExtra(NewGameActivity.J2));
            }
            else{
                g.addJoueurs("Joueur 2");
                g.addScore("Joueur 2");
            }
            if(i.getStringExtra(NewGameActivity.J3).length()>=1){
                g.addJoueurs(i.getStringExtra(NewGameActivity.J3));
                g.addScore(i.getStringExtra(NewGameActivity.J3));
            }
            else{
                g.addJoueurs("Joueur 3");
                g.addScore("Joueur 3");
            }
            if(!i.getStringExtra(NewGameActivity.J4).equals("no")) {
                if (i.getStringExtra(NewGameActivity.J4).length() >= 1) {
                    g.addJoueurs(i.getStringExtra(NewGameActivity.J4));
                    g.addScore(i.getStringExtra(NewGameActivity.J4));
                }
                else {
                    g.addJoueurs("Joueur 4");
                    g.addScore("Joueur 4");
                }
            }
            if(!i.getStringExtra(NewGameActivity.J5).equals("no")){
                if(i.getStringExtra(NewGameActivity.J5).length()>=1){
                    g.addJoueurs(i.getStringExtra(NewGameActivity.J5));
                    g.addScore(i.getStringExtra(NewGameActivity.J5));
                }
                else{
                    g.addJoueurs("Joueur 5");
                    g.addScore("Joueur 5");
                }
            }

            dataGame.newData(g);
        }

        else if(MainActivity.load){
            g = MainActivity.game;
        }

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.custom_style, g.getScoring());
        grid.setNumColumns(g.getJoueurs().size()+1);
        System.out.println("TEST :: Taille = " + g.getJoueurs().size());
        grid.setAdapter(arrayAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_newHand = new Intent(GameActivity.this, NewHandActivity.class);
                intent_newHand.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent_newHand.putStringArrayListExtra(JS, g.getJoueurs());
                startActivityForResult(intent_newHand, CODE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == GameActivity.RESULT_OK){
            tour++;
            scores = data.getIntegerArrayListExtra(NewHandActivity.SCORING);
            g.addScore("" + tour);
            if(g.getScoring().size()<g.getJoueurs().size()*2){
                for(int i=0; i<grid.getNumColumns()-1;i++){
                    g.addScore("" + scores.get(i));
                }
            }
            else {
                for (int i = 0; i < grid.getNumColumns() - 1; i++) {
                    int old = Integer.parseInt(g.getScoring().get(g.getScoring().size() - g.getJoueurs().size() - 1).toString());
                    int news = Integer.parseInt(scores.get(i).toString());
                    int sold = old + news;
                    String soldParsed = String.valueOf(sold);
                    g.addScore("" + soldParsed);
                }
            }
            grid.setAdapter(arrayAdapter);
            dataGame.updateGame(g);
            dataGame.getAll();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent_close = new Intent(GameActivity.this, MainActivity.class);
        intent_close.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        dataGame.close();
        startActivity(intent_close);
    }
}