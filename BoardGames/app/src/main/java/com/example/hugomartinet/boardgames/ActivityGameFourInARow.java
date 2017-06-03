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

import java.util.Arrays;

public class ActivityGameFourInARow extends AppCompatActivity implements View.OnClickListener {

    private String name1;
    private String name2;
    private int currentPlayer;
    private FourInARowGame myGame = new FourInARowGame();

    private Integer[][] rings = {{R.id.ring00,R.id.ring01,R.id.ring02,R.id.ring03,R.id.ring04,R.id.ring05,R.id.ring06},
            {R.id.ring10,R.id.ring11,R.id.ring12,R.id.ring13,R.id.ring14,R.id.ring15,R.id.ring16},
            {R.id.ring20,R.id.ring21,R.id.ring22,R.id.ring23,R.id.ring24,R.id.ring25,R.id.ring26},
            {R.id.ring30,R.id.ring31,R.id.ring32,R.id.ring33,R.id.ring34,R.id.ring35,R.id.ring36},
            {R.id.ring40,R.id.ring41,R.id.ring42,R.id.ring43,R.id.ring44,R.id.ring45,R.id.ring46},
            {R.id.ring50,R.id.ring51,R.id.ring52,R.id.ring53,R.id.ring54,R.id.ring55,R.id.ring56},
            {R.id.ring60,R.id.ring61,R.id.ring62,R.id.ring63,R.id.ring64,R.id.ring65,R.id.ring66}};

    private Integer[] buttons = {R.id.column1,R.id.column2,R.id.column3,R.id.column4,R.id.column5,R.id.column6,R.id.column7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_four_in_arow);

        TextView tx = (TextView)findViewById(R.id.titleFourInARowGame);
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

        this.myGame.setNumberOfPlayers(nbOfPlayers);
        this.myGame.setComputerLevel(computerLvl);
        this.myGame.setStarter(1);


        this.currentPlayer = 1;


        if (this.name1.equals("")) {
            this.name1 = "Player 1";
        }

        if (this.name2.equals("")) {
            this.name2 = "Player 2";
        }

        TextView n1 = (TextView)findViewById(R.id.player1);
        n1.setText(this.name1);
        TextView n2 = (TextView)findViewById(R.id.player2);
        n2.setText(this.name2);

        for(int i:buttons){
            Button but = (Button)findViewById(i);
            but.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int column = Arrays.asList(this.buttons).indexOf(v.getId());
        this.myGame.move(currentPlayer,column);
        int row = this.myGame.getLastRow(column);
        int chipID = this.rings[column][row];
        ImageView chip = (ImageView)findViewById(chipID);

        if (currentPlayer == 1){
            chip.setBackgroundResource(R.drawable.redring);
        } else {
            chip.setBackgroundResource(R.drawable.yellowring);
        }


        if(this.myGame.gameFinished()) {
            throwAlert(this.myGame.getWinner());
        } else {
            changePlayer();
            if(this.myGame.columnFilled(this.myGame.getGrid(), column)) {
                ((Button) v).setEnabled(false);
            }

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

    private void throwAlert(int winner) {
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
                        startActivity(new Intent(ActivityGameFourInARow.this, MainActivity.class));
                    }
                })
                .setTitle("Game Over");

        AlertDialog dialog = endGame.create();
        dialog.show();

        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.BLUE);
    }

    private void changePlayer() {
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

        int starter = this.myGame.getStarter();
        int nb = this.myGame.getNumberOfPlayers();
        int lvl = this.myGame.getComputerLevel();

        this.myGame = new FourInARowGame();
        this.myGame.setNumberOfPlayers(nb);
        this.myGame.setComputerLevel(lvl);
        this.myGame.setStarter((starter)%2 + 1);
        this.currentPlayer = this.myGame.getStarter();

        if (this.myGame.getStarter() == 1) {
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

        for (Integer[] idcol: rings){
            for( Integer id : idcol){
                ImageView chip = (ImageView)findViewById(id);
                chip.setBackgroundResource(R.drawable.emptyring);
            }
        }

        if (this.myGame.getNumberOfPlayers() == 1 && this.myGame.getStarter() == 2){
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

    private void compMove() {
        int choice = this.myGame.computerMove();
        int row = this.myGame.getLastRow(choice);
        int chipID = this.rings[choice][row];
        ImageView chip = (ImageView)findViewById(chipID);
        chip.setBackgroundResource(R.drawable.yellowring);
        if(this.myGame.gameFinished()) {
            throwAlert(this.myGame.getWinner());
        }

        changePlayer();
        enableButtons();
    }

    private void enableButtons(){
        for (int id: buttons){
            Button b = (Button)findViewById(id);
            int col = Arrays.asList(buttons).indexOf(id);
            if (!this.myGame.columnFilled(this.myGame.getGrid(), col)){
                b.setEnabled(true);
            }
        }
    }

    private void disableButtons() {
        for (int i:buttons){
            Button but = (Button)findViewById(i);
            but.setEnabled(false);
        }
    }
}
