package com.example.hugomartinet.boardgames;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
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

        TicTacToeGame myGame = new TicTacToeGame(numberOfPlayers, starter, computerLvl);


    }

    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        GridLayout myGrid = (GridLayout)findViewById(R.id.gridLayout);
        if(x > 45 && x < 130 && y > 295 && y < 380){
            ImageView newimg = new ImageView(this);
            newimg.setImageResource(R.drawable.circlettt);
            newimg.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        return false;
    }


}
