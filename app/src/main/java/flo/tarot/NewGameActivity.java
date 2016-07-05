package flo.tarot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class NewGameActivity extends AppCompatActivity {
    public EditText j1, j2, j3, j4, j5;
    protected final static String J1 = "joueur 1";
    protected final static String J2 = "joueur 2";
    protected final static String J3 = "joueur 3";
    protected final static String J4 = "joueur 4";
    protected final static String J5 = "joueur 5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabadd = (FloatingActionButton) findViewById(R.id.fabadd);
        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (j4.getVisibility() == View.INVISIBLE) {
                    j4.setVisibility(View.VISIBLE);
                } else {
                    j5.setVisibility(View.VISIBLE);
                }
            }
        });

        FloatingActionButton fabless = (FloatingActionButton) findViewById(R.id.fabless);
        fabless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(j5.getVisibility() == View.VISIBLE){
                    j5.setVisibility(View.INVISIBLE);
                }
                else{
                    j4.setVisibility(View.INVISIBLE);
                }
            }
        });

        j1 = (EditText) findViewById(R.id.j1);
        j2 = (EditText) findViewById(R.id.j2);
        j3 = (EditText) findViewById(R.id.j3);
        j4 = (EditText) findViewById(R.id.j4);
        j5 = (EditText) findViewById(R.id.j5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        Intent intent_newGame = new Intent(NewGameActivity.this, GameActivity.class);
        intent_newGame.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent_newGame.putExtra(J1, j1.getText().toString());
        intent_newGame.putExtra(J2, j2.getText().toString());
        intent_newGame.putExtra(J3, j3.getText().toString());
        if(j4.getVisibility() == View.VISIBLE){
            intent_newGame.putExtra(J4, j4.getText().toString());
        }
        else{
            intent_newGame.putExtra(J4, "no");
        }
        if(j5.getVisibility() == View.VISIBLE){
            intent_newGame.putExtra(J5, j5.getText().toString());
        }
        else{
            intent_newGame.putExtra(J5, "no");
        }
        finish();
        MainActivity.load = false;
        startActivity(intent_newGame);
        return super.onOptionsItemSelected(item);
    }
}
