package flo.tarot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView liste;
    static private String[] menu = {"Nouvelle Partie", "Charger Partie"};
    static public Game game;
    static public boolean load = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        liste = (ListView) findViewById(R.id.listView);
        liste.setAdapter(new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, menu));

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent_main = null;
                if (position == 0){
                    intent_main = new Intent(MainActivity.this, NewGameActivity.class);
                }
                else if (position == 1){
                    intent_main = new Intent(MainActivity.this, LoadGameActivity.class);
                }
                intent_main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent_main);
            }
        });
    }
}