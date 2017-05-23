package com.example.hugomartinet.boardgames;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ActivityTicTacToe extends AppCompatActivity {

    private int computerLvl = 0;
    private int numberOfPlayers = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        TextView tx = (TextView) findViewById(R.id.titleTicTacToe);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/introinline.otf");
        tx.setTypeface(custom_font);

    }

    public void onClickPVP(View view){
        RadioGroup radGroup = (RadioGroup)findViewById(R.id.radioGroup1);
        EditText name2 = (EditText)findViewById(R.id.editText2);
        name2.setText("");
        name2.setEnabled(true);
        radGroup.setVisibility(View.INVISIBLE);
        this.numberOfPlayers = 2;
    }

    public void onClickPVC(View view){
        RadioGroup radGroup = (RadioGroup)findViewById(R.id.radioGroup1);
        EditText name2 = (EditText)findViewById(R.id.editText2);
        name2.setText("Computer");
        name2.setEnabled(false);
        radGroup.setVisibility(View.VISIBLE);
        this.numberOfPlayers = 1;
    }

    public void onClick1(View view){
        this.computerLvl = 1;
    }

    public void onClick2(View view){
        this.computerLvl = 2;
    }

    public void onClick3(View view){
        this.computerLvl = 3;
    }

    public void onClickStart(View view) {
        EditText name1 = (EditText)findViewById(R.id.editText1);
        EditText name2 = (EditText)findViewById(R.id.editText2);
        Intent myIntent = new Intent(ActivityTicTacToe.this, ActivityGameTicTacToe.class);

        myIntent.putExtra("namePlayer1",name1.getText().toString());
        myIntent.putExtra("namePlayer2",name2.getText().toString());
        myIntent.putExtra("numberOfPlayers",this.numberOfPlayers);
        myIntent.putExtra("computerLvl",this.computerLvl);

        startActivity(myIntent);
    }

}
