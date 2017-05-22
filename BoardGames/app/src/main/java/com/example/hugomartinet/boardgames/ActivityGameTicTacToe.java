package com.example.hugomartinet.boardgames;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityGameTicTacToe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_tic_tac_toe);

        TextView tx = (TextView) findViewById(R.id.titleTicTacToeGame);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/introinline.otf");
        tx.setTypeface(custom_font);

        TextView s1 = (TextView) findViewById(R.id.score1);
        TextView s2 = (TextView) findViewById(R.id.score2);
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(), "fonts/Intro.otf");
        s1.setTypeface(custom_font2);
        s2.setTypeface(custom_font2);

        System.out.println("OK");

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        String name1 = (String) b.get("namePlayer1");
        String name2 = (String) b.get("namePlayer2");
        int numberOfPlayers = (Integer) b.get("numberOfPlayers");
        int computerLvl = (Integer) b.get("computerLvl");

        int starter = 1;

        TicTacToeGame myGame = new TicTacToeGame(numberOfPlayers, starter, 1);

    }


}
