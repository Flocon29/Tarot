package flo.tarot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LoadGameActivity extends AppCompatActivity {
    private ListView liste;
    private final GameData dataGame = new GameData(this);
    static private Game[] games;
    private Game select;
    protected final static String GAME = "load";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataGame.open();

        liste = (ListView) findViewById(R.id.loadList);
        loadAdapter(dataGame);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(LoadGameActivity.this, GameActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                MainActivity.game= games[position];
                dataGame.close();
                MainActivity.load = true;
                startActivity(i);
            }
        });

        liste.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                select = games[position];
                dataGame.removeGame(select.getId());
                liste.setAdapter(null);
                loadAdapter(dataGame);
                return true;
            }
        });
    }

    public void loadAdapter(GameData dataGame){
        games = dataGame.getAll();
        ArrayList parties = new ArrayList();
        if (games.length != 0){
            for (int i = 0; i < games.length; i++){
                parties.add(games[i].getJoueurs());
            }
            liste.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, parties));
        }
    }
}
