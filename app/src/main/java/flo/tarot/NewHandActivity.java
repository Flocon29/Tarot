package flo.tarot;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class NewHandActivity extends AppCompatActivity {
    private Spinner preneur, appel, mise, poignee, misere;
    private Object item;
    private ArrayList joueurs = new ArrayList();
    private ArrayList scoring = new ArrayList();
    private ArrayList namePoignee = new ArrayList();
    private ArrayList nameMisere = new ArrayList();
    private Integer[] points = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,
                                32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,
                                61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91};
    private Integer[] paliers = {56,51,41,36};
    private int[] scores = {0,0,0,0,0};
    private String[] mises = {"Petite", "Garde", "Garde Sans", "Garde Contre"};
    private String[] poings = {"Aucune", "Simple", "Double", "Triple"};
    private String[] miseres = {"Aucune", "Têtes", "Atouts"};
    private TextView textPoigne, textMisere, textPetit, appelView, boutsNb, pointsNb;
    private Switch switchPetit;
    private Intent intentResult = new Intent();
    protected final static String SCORING = "score des joueurs";
    private int base = 20, playerPetit, addPoints;
    private int signe = -1;
    private String namePetit;
    private SeekBar boutsBar, pointsBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hand);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        i.getStringArrayListExtra(GameActivity.JS).toString();
        for(int j=0;j<i.getStringArrayListExtra(GameActivity.JS).size();j++) {
            joueurs.add(i.getStringArrayListExtra(GameActivity.JS).get(j));
        }

        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_multichoice, joueurs);
        final ArrayAdapter pointsAdapter = new ArrayAdapter (this, android.R.layout.select_dialog_singlechoice,points);

        preneur = (Spinner) findViewById(R.id.spinPreneur);
        appelView = (TextView) findViewById(R.id.appelView);
        appel = (Spinner) findViewById(R.id.spinAppel);
        mise = (Spinner) findViewById(R.id.spinMise);
        poignee = (Spinner) findViewById(R.id.spinPoing);
        misere = (Spinner) findViewById(R.id.spinMisere);
        textPoigne = (TextView) findViewById(R.id.textPoigne);
        textMisere = (TextView) findViewById(R.id.textMisere);
        switchPetit = (Switch) findViewById(R.id.switchPetit);
        textPetit = (TextView) findViewById(R.id.textPetit);
        boutsBar = (SeekBar) findViewById(R.id.boutsBar);
        pointsBar = (SeekBar) findViewById(R.id.pointsBar);
        boutsNb = (TextView) findViewById(R.id.boutsNb);
        pointsNb = (TextView) findViewById(R.id.pointsNb);

        preneur.setAdapter(new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, joueurs));
        if(joueurs.size() == 5){
            appel.setVisibility(View.VISIBLE);
            appelView.setVisibility(View.VISIBLE);
            appel.setAdapter(new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, joueurs));
        }
        mise.setAdapter(new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, mises));
        poignee.setAdapter(new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, poings));
        misere.setAdapter(new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, miseres));

        poignee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                item = parent.getItemAtPosition(pos);
                if (item != "Aucune") {
                    adb.setTitle("Quels joueurs ont déclarés leur poignée?");
                    adb.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    adb.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            namePoignee.add(arrayAdapter.getItem(which));
                            textPoigne.setText(item + " : " + namePoignee);
                        }
                    });
                    adb.show();
                }
                if (item == "Aucune") {
                    textPoigne.setText(null);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        misere.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                item = parent.getItemAtPosition(pos);
                if (item != "Aucune") {
                    adb.setTitle("Quels joueurs ont déclarés leur poignée?");
                    adb.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    adb.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            nameMisere.add(arrayAdapter.getItem(which));
                            textMisere.setText(item + " : " + nameMisere);
                        }
                    });
                    adb.show();
                }
                if (item == "Aucune") {
                    textMisere.setText(null);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        switchPetit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    adb.setTitle("Quels joueurs ont déclarés leur poignée?");
                    adb.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    adb.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            playerPetit = (int) arrayAdapter.getItemId(which);
                            namePetit = arrayAdapter.getItem(which);
                            textPetit.setText("Petit au bout : " + namePetit);
                        }
                    });
                    adb.show();
                } else {
                    textPetit.setText(null);
                }
            }

        });

        boutsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                boutsNb.setText("" + seekBar.getProgress());
            }
        });

        pointsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pointsNb.setText("" + seekBar.getProgress());
            }
        });

        pointsNb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adb.setTitle("Points du preneur");
                adb.setAdapter(pointsAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pointsNb.setText("" + pointsAdapter.getItemId(which));
                        pointsBar.setProgress((int) pointsAdapter.getItemId(which));
                    }
                });
                adb.show();
            }
        });
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
        if (id == R.id.action_save) {
            addPoints = pointsBar.getProgress() - paliers[boutsBar.getProgress()];

            if(mise.getSelectedItem() == "Garde"){
                base = base*2;
            } else if (mise.getSelectedItem() == "Garde Sans"){
                base = base*4;
            } else if (mise.getSelectedItem() == "Garde Contre"){
                base = base*8;
            }

            if (addPoints >= 0){
                for (int i=0; i<joueurs.size(); i++){
                    scores[i] = - base - addPoints;
                }
            }
            else {
                for (int i=0; i<joueurs.size(); i++){
                    scores[i] = base - addPoints;
                }
            }

            if (joueurs.size() == 5) {
                if (preneur.getSelectedItemPosition() == appel.getSelectedItemPosition()) {
                    scores[preneur.getSelectedItemPosition()] = scores[preneur.getSelectedItemPosition()] * (4) * signe;
                } else {
                    scores[preneur.getSelectedItemPosition()] = scores[preneur.getSelectedItemPosition()] * (2) * signe;
                    scores[appel.getSelectedItemPosition()] = scores[appel.getSelectedItemPosition()] * signe;
                }
            } else if (joueurs.size() == 4) {
                scores[preneur.getSelectedItemPosition()] = scores[preneur.getSelectedItemPosition()] * (3) * signe;
            } else if (joueurs.size() == 3) {
                scores[preneur.getSelectedItemPosition()] = scores[preneur.getSelectedItemPosition()] * (2) * signe;
            }

            for (int i=0; i<joueurs.size(); i++){
                if(!poignee.getSelectedItem().equals("Aucune")) {
                    for(int j=0; j<namePoignee.size(); j++){
                        if (!joueurs.get(i).equals(namePoignee.get(j))) {
                            scores[i] += -10 * poignee.getSelectedItemPosition();
                        }
                        else if (joueurs.get(i).equals(namePoignee.get(j))) {
                            scores[i] += 10 * (joueurs.size() - 1) * poignee.getSelectedItemPosition();
                        }
                    }
                }
                if(!misere.getSelectedItem().equals("Aucune")){
                    for(int j=0; j<nameMisere.size(); j++){
                        if (!joueurs.get(i).equals(nameMisere.get(j))){
                            scores[i] += -10;
                        }
                        else if (joueurs.get(i).equals(nameMisere.get(j))) {
                            scores[i] += 10 * (joueurs.size() - 1);
                        }
                    }
                }
                if (switchPetit.isChecked()){
                    if (i != playerPetit) {
                        scores[i] += -10;
                    }
                    else if (i == playerPetit){
                        scores[i] += 10 * (joueurs.size() - 1);
                    }
                }
                scoring.add(scores[i]);
            }

            intentResult.putExtra(SCORING, scoring);
            setResult(GameActivity.RESULT_OK, intentResult);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
