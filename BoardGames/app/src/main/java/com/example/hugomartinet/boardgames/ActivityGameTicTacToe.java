package com.example.hugomartinet.boardgames;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.logging.LogRecord;

public class ActivityGameTicTacToe extends AppCompatActivity implements View.OnClickListener {

    private int currentPlayer;
    private TicTacToeGame myGame = new TicTacToeGame();
    private String name1;
    private String name2;
    private Integer[] buttons = {R.id.button1,R.id.button2,R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9};

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

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        this.name1 = (String) b.get("namePlayer1");
        this.name2 = (String) b.get("namePlayer2");
        int nbOfPlayers = (int) b.get("numberOfPlayers");
        int computerLvl = (int) b.get("computerLvl");


        if (this.name1 != "") {
            TextView n1 = (TextView)findViewById(R.id.player1);
            n1.setText(this.name1);
        }

        if (this.name2 != "") {
            TextView n2 = (TextView)findViewById(R.id.player2);
            n2.setText(this.name2);
        }

        this.myGame.setNumberOfPlayers(nbOfPlayers);
        this.myGame.setComputerLevel(computerLvl);
        this.myGame.starter = 1;

        this.currentPlayer = 1;


        for(int i: buttons){
            Button but = (Button)findViewById(i);
            but.setOnClickListener(this);
        }
    }

    public void throwAlert(int winner) {
        AlertDialog.Builder endGame = new AlertDialog.Builder(this);
        if (winner == 0) {
            endGame.setMessage("Nobody wins...");
        }
        else if (winner == 1){
            endGame.setMessage(this.name1 + " wins !");
        }
        else {
            endGame.setMessage(this.name2 + " wins !");
        }
        endGame.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                newGame();
            }
        })
                .setNeutralButton("Go Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(ActivityGameTicTacToe.this, MainActivity.class));
                    }
                })
                .setTitle("Game Over");

        AlertDialog dialog = endGame.create();
        dialog.show();

        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.BLUE);


    }




    @Override
    public void onClick(View v) {
        final Button b = (Button) v;
        if (this.currentPlayer == 1){
            b.setBackgroundResource(R.drawable.crossttt);
        }
        else {
            b.setBackgroundResource(R.drawable.circlettt);
        }
        int buttonNb = Arrays.asList(this.buttons).indexOf(v.getId());
        int row = buttonNb/3;
        int col = buttonNb%3;

        this.myGame.move(this.currentPlayer,row,col);

        if(this.myGame.isFinished) {
            throwAlert(this.myGame.getWinner());
        } else {
            changePlayer();
            b.setEnabled(false);

            if (this.myGame.getNumberOfPlayers() == 1) {
                disableButtons();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        compMove();
                    }
                }, 700);
            }
        }
    }

    public void compMove(){
        int[] choice = this.myGame.computerMove();
        int index = choice[0] * 3 + choice[1];
        int id = this.buttons[index];

        Button compB = (Button) findViewById(id);
        compB.setEnabled(false);
        compB.setBackgroundResource(R.drawable.circlettt);

        if(this.myGame.isFinished) {
            throwAlert(this.myGame.getWinner());
        }

        changePlayer();
        enableButtons();
    }

    public void changePlayer(){
        ImageView underline1 = (ImageView)findViewById(R.id.underlinepl1);
        ImageView underline2 = (ImageView)findViewById(R.id.underlinepl2);
        if (currentPlayer == 1) {
            underline1.setVisibility(View.INVISIBLE);
            underline2.setVisibility(View.VISIBLE);
        } else {
            underline1.setVisibility(View.VISIBLE);
            underline2.setVisibility(View.INVISIBLE);
        }
        this.currentPlayer = (this.currentPlayer)%2 + 1;
    }

    public void enableButtons(){
        int[][] grid = this.myGame.grid;
        for (int i:buttons){
            Button but = (Button)findViewById(i);
            int buttonNb = Arrays.asList(this.buttons).indexOf(i);
            int row = buttonNb/3;
            int col = buttonNb%3;
            if (grid[row][col] == 0) {
                but.setEnabled(true);
            }
        }
    }

    public void disableButtons(){
        int[][] grid = this.myGame.grid;
        for (int i:buttons){
            Button but = (Button)findViewById(i);
            but.setEnabled(false);
        }
    }

    public void newGame(){
        int winner = this.myGame.getWinner();
        if (winner == 1) {
            TextView score1 = (TextView)findViewById(R.id.score1);
            String sc1 = (String) score1.getText();
            Integer value = Integer.parseInt(sc1);
            value++;
            score1.setText(value.toString());
        }
        else if (winner == 2) {
            TextView score2 = (TextView)findViewById(R.id.score2);
            String sc2 = (String) score2.getText();
            Integer value = Integer.parseInt(sc2);
            value++;
            score2.setText(value.toString());
        }

        int starter = this.myGame.starter;
        int nb = this.myGame.getNumberOfPlayers();
        int lvl = this.myGame.getComputerLevel();

        this.myGame = new TicTacToeGame();
        this.myGame.setNumberOfPlayers(nb);
        this.myGame.setComputerLevel(lvl);
        this.myGame.starter = (starter)%2 + 1;
        this.currentPlayer = this.myGame.starter;

        if (this.myGame.starter == 1) {
            ImageView underline1 = (ImageView)findViewById(R.id.underlinepl1);
            ImageView underline2 = (ImageView)findViewById(R.id.underlinepl2);
            underline1.setVisibility(View.VISIBLE);
            underline2.setVisibility(View.INVISIBLE);
        }
        else {
            ImageView underline1 = (ImageView)findViewById(R.id.underlinepl1);
            ImageView underline2 = (ImageView)findViewById(R.id.underlinepl2);
            underline1.setVisibility(View.INVISIBLE);
            underline2.setVisibility(View.VISIBLE);
        }

        for (int i:buttons){
            Button but = (Button)findViewById(i);
            but.setEnabled(true);
            but.setBackgroundResource(R.drawable.empty);
        }

        if (this.myGame.getNumberOfPlayers() == 1 && this.myGame.starter == 2){
            disableButtons();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    compMove();
                }
            }, 1000);
        }
    }
}
