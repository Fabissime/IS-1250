package com.example.hugomartinet.boardgames;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityGameDots extends AppCompatActivity {

    private String name1;
    private String name2;
    private int currentPlayer;
    private DotsGame myGame = new DotsGame();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_dots);

        TextView tx = (TextView)findViewById(R.id.titleDots);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/introinline.otf");
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

        this.myGame.setNumberOfPlayers(nbOfPlayers);
        this.myGame.setComputerLevel(computerLvl);
        this.myGame.setStarter(1);

        this.currentPlayer = 1;


        DotsBoard myBoard = (DotsBoard)findViewById(R.id.dotsBoard);
        myBoard.delegate = this.myGame;

        myBoard.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                DotsBoard db = (DotsBoard) v;
                float x = event.getX();
                float y = event.getY();

                int[][] closests = closestDots(new float[] {x,y});

                int[] start = closests[0];
                int[] end = closests[1];

                onClick(start, end, currentPlayer, db);
                return false;
            }

        });

    }

    public int[][] closestDots(float[] point){
        float x = point[0];
        float y = point[1];

        int row = (int) ((y-15)/125);
        int col = (int) ((x-15)/174);

        float x0 = 15 + 174*col;
        float y0 = 15 + 125*row;

        float r1 = (x - x0)/174;
        float r2 = (y - y0)/125;

        int[] start;
        int[] end;

        if(r1+r2 < 1){
            start = new int[] {row,col};
            if (r1 < r2){
                end = new int[] {row+1,col};
            } else {
                end = new int[] {row,col+1};
            }
        } else {
            end = new int[] {row+1,col+1};
            if (r1 < r2){
                start = new int[] {row+1,col};
            } else {
                start = new int[] {row,col+1};
            }
        }
        return new int[][] {start,end};
    }


    private int distance(int[] closest, int[] point) {
        return (int) Math.sqrt((closest[0]-point[0])*(closest[0]-point[0]) + (closest[1]-point[1])*(closest[1]-point[1]));
    }


    public void onClick(int[] start, int[] end, int player, final DotsBoard db){
        if(currentPlayer == 1 || this.myGame.getNumberOfPlayers() == 2){
            if(!this.myGame.contains(start,end,this.myGame.getEdges())) {
                int scored = this.myGame.addEdge(new Edge(start, end, player), this.myGame.getEdges(),true);
                this.myGame.checkEndOfGame();
                db.delegate = this.myGame;
                db.invalidate();
                if (scored == 0) {
                    changePlayer();
                    if (this.myGame.getNumberOfPlayers() == 1) {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {compMove(db);}}, 700);
                    }
                } else {
                    if (currentPlayer == 1) {
                        TextView score1 = (TextView) findViewById(R.id.actualsc1);
                        String sc1 = (String) score1.getText();
                        Integer value = Integer.parseInt(sc1);
                        value = value + scored;
                        score1.setText(value.toString());
                    } else {
                        TextView score2 = (TextView) findViewById(R.id.actualsc2);
                        String sc2 = (String) score2.getText();
                        Integer value = Integer.parseInt(sc2);
                        value += scored;
                        score2.setText(value.toString());
                    }
                }
                if (this.myGame.isFinished()) {
                    TextView score1 = (TextView) findViewById(R.id.actualsc1);
                    String sc1 = (String) score1.getText();
                    Integer value1 = Integer.parseInt(sc1);
                    TextView score2 = (TextView) findViewById(R.id.actualsc2);
                    String sc2 = (String) score2.getText();
                    Integer value2 = Integer.parseInt(sc2);
                    if (value1 > value2) {
                        this.myGame.setWinner(1);
                    } else if (value1 < value2) {
                        this.myGame.setWinner(2);
                    } else {
                        this.myGame.setWinner(0);
                    }
                    throwAlert(this.myGame.getWinner(), db);
                }
            }
        }
    }

    public void throwAlert(int winner, final DotsBoard db) {
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
                newGame(db);
            }
        })
                .setNeutralButton("Go Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(ActivityGameDots.this, MainActivity.class));
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

    public void newGame(final DotsBoard db){
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

        TextView score1 = (TextView) findViewById(R.id.actualsc1);
        Integer value = 0;
        score1.setText(value.toString());

        TextView score2 = (TextView) findViewById(R.id.actualsc2);
        score2.setText(value.toString());

        int starter = this.myGame.getStarter();
        int nb = this.myGame.getNumberOfPlayers();
        int lvl = this.myGame.getComputerLevel();

        this.myGame = new DotsGame();
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

        this.myGame.setEdges(new ArrayList<Edge>());
        this.myGame.setRectangles(new ArrayList<Rectangle>());
        db.delegate = this.myGame;
        db.invalidate();

        if (this.myGame.getNumberOfPlayers() == 1 && this.myGame.getStarter() == 2){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    compMove(db);
                }
            }, 1000);
        }
    }

    private void compMove(final DotsBoard db) {
        int scored = this.myGame.computerMove();
        this.myGame.checkEndOfGame();
        db.delegate = this.myGame;
        db.invalidate();
        if (scored == 0) {
            changePlayer();
        } else {
            TextView score2 = (TextView) findViewById(R.id.actualsc2);
            String sc2 = (String) score2.getText();
            Integer value = Integer.parseInt(sc2);
            value += scored;
            score2.setText(value.toString());
            if (this.myGame.isFinished()) {
                TextView score1 = (TextView) findViewById(R.id.actualsc1);
                String sc1 = (String) score1.getText();
                Integer value1 = Integer.parseInt(sc1);
                Integer value2 = value;
                if (value1 > value2) {
                    this.myGame.setWinner(1);
                } else if (value1 < value2) {
                    this.myGame.setWinner(2);
                } else {
                    this.myGame.setWinner(0);
                }
                throwAlert(this.myGame.getWinner(), db);
            } else {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {compMove(db);}}, 700);
            }
        }
    }
}
