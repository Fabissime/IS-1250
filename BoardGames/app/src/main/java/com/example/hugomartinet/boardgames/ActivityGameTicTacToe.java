package com.example.hugomartinet.boardgames;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivityGameTicTacToe extends AppCompatActivity {

    private int currentPlayer;
    private TicTacToeGame myGame = new TicTacToeGame();
    private String name1;
    private String name2;

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

        this.name1 = (String) b.get("namePlayer1");
        this.name2 = (String) b.get("namePlayer2");
        int nbOfPlayers = (int) b.get("numberOfPlayers");
        Integer computerLvl = (Integer) b.get("computerLvl");


        if (this.name1 != "") {
            TextView n1 = (TextView)findViewById(R.id.player1);
            n1.setText(this.name1);
        }

        if (this.name2 != "") {
            TextView n2 = (TextView)findViewById(R.id.player2);
            n2.setText(this.name2);
        }

        this.myGame.setNumberOfPlayers(nbOfPlayers);
        this.myGame.setNextPlayer(1);
        this.myGame.setComputerLevel(computerLvl);
        this.myGame.starter = 1;

        this.currentPlayer = 1;

    }

    public void clickButton1(View view){
        Button but = (Button)findViewById(R.id.button1);
        if (this.currentPlayer == 1) {
            but.setBackgroundResource(R.drawable.crossttt);
        }
        else {
            but.setBackgroundResource(R.drawable.circlettt);
        }

        this.myGame.move(this.currentPlayer,0,0);

        if(this.myGame.isFinished) {
            throwAlert(view);
        }

        this.currentPlayer = (this.currentPlayer)%2 + 1;
        but.setEnabled(false);

        ImageView underline1 = (ImageView)findViewById(R.id.underlinepl1);
        ImageView underline2 = (ImageView)findViewById(R.id.underlinepl2);
        if (underline1.getVisibility() == View.VISIBLE){
            underline1.setVisibility(View.INVISIBLE);
            underline2.setVisibility(View.VISIBLE);
        }
        else {
            underline1.setVisibility(View.VISIBLE);
            underline2.setVisibility(View.INVISIBLE);
        }
    }

    public void clickButton2(View view){
        Button but = (Button)findViewById(R.id.button2);
        if (this.currentPlayer == 1) {
            but.setBackgroundResource(R.drawable.crossttt);
        }
        else {
            but.setBackgroundResource(R.drawable.circlettt);
        }

        this.myGame.move(this.currentPlayer,0,1);

        if(this.myGame.isFinished) {
            throwAlert(view);
        }

        this.currentPlayer = (this.currentPlayer)%2 + 1;
        but.setEnabled(false);
        ImageView underline1 = (ImageView)findViewById(R.id.underlinepl1);
        ImageView underline2 = (ImageView)findViewById(R.id.underlinepl2);
        if (underline1.getVisibility() == View.VISIBLE){
            underline1.setVisibility(View.INVISIBLE);
            underline2.setVisibility(View.VISIBLE);
        }
        else {
            underline1.setVisibility(View.VISIBLE);
            underline2.setVisibility(View.INVISIBLE);
        }
    }

    public void clickButton3(View view){
        Button but = (Button)findViewById(R.id.button3);
        if (this.currentPlayer == 1) {
            but.setBackgroundResource(R.drawable.crossttt);
        }
        else {
            but.setBackgroundResource(R.drawable.circlettt);
        }

        this.myGame.move(this.currentPlayer,0,2);

        if(this.myGame.isFinished) {
            throwAlert(view);
        }

        this.currentPlayer = (this.currentPlayer)%2 + 1;
        but.setEnabled(false);
        ImageView underline1 = (ImageView)findViewById(R.id.underlinepl1);
        ImageView underline2 = (ImageView)findViewById(R.id.underlinepl2);
        if (underline1.getVisibility() == View.VISIBLE){
            underline1.setVisibility(View.INVISIBLE);
            underline2.setVisibility(View.VISIBLE);
        }
        else {
            underline1.setVisibility(View.VISIBLE);
            underline2.setVisibility(View.INVISIBLE);
        }
    }

    public void clickButton4(View view){
        Button but = (Button)findViewById(R.id.button4);
        if (this.currentPlayer == 1) {
            but.setBackgroundResource(R.drawable.crossttt);
        }
        else {
            but.setBackgroundResource(R.drawable.circlettt);
        }

        this.myGame.move(this.currentPlayer,1,0);

        if(this.myGame.isFinished) {
            throwAlert(view);
        }

        this.currentPlayer = (this.currentPlayer)%2 + 1;
        but.setEnabled(false);
        ImageView underline1 = (ImageView)findViewById(R.id.underlinepl1);
        ImageView underline2 = (ImageView)findViewById(R.id.underlinepl2);
        if (underline1.getVisibility() == View.VISIBLE){
            underline1.setVisibility(View.INVISIBLE);
            underline2.setVisibility(View.VISIBLE);
        }
        else {
            underline1.setVisibility(View.VISIBLE);
            underline2.setVisibility(View.INVISIBLE);
        }
    }

    public void clickButton5(View view){
        Button but = (Button)findViewById(R.id.button5);
        if (this.currentPlayer == 1) {
            but.setBackgroundResource(R.drawable.crossttt);
        }
        else {
            but.setBackgroundResource(R.drawable.circlettt);
        }

        this.myGame.move(this.currentPlayer,1,1);

        if(this.myGame.isFinished) {
            throwAlert(view);
        }

        this.currentPlayer = (this.currentPlayer)%2 + 1;
        but.setEnabled(false);
        ImageView underline1 = (ImageView)findViewById(R.id.underlinepl1);
        ImageView underline2 = (ImageView)findViewById(R.id.underlinepl2);
        if (underline1.getVisibility() == View.VISIBLE){
            underline1.setVisibility(View.INVISIBLE);
            underline2.setVisibility(View.VISIBLE);
        }
        else {
            underline1.setVisibility(View.VISIBLE);
            underline2.setVisibility(View.INVISIBLE);
        }
    }

    public void clickButton6(View view){
        Button but = (Button)findViewById(R.id.button6);
        if (this.currentPlayer == 1) {
            but.setBackgroundResource(R.drawable.crossttt);
        }
        else {
            but.setBackgroundResource(R.drawable.circlettt);
        }

        this.myGame.move(this.currentPlayer,1,2);

        if(this.myGame.isFinished) {
            throwAlert(view);
        }

        this.currentPlayer = (this.currentPlayer)%2 + 1;
        but.setEnabled(false);
        ImageView underline1 = (ImageView)findViewById(R.id.underlinepl1);
        ImageView underline2 = (ImageView)findViewById(R.id.underlinepl2);
        if (underline1.getVisibility() == View.VISIBLE){
            underline1.setVisibility(View.INVISIBLE);
            underline2.setVisibility(View.VISIBLE);
        }
        else {
            underline1.setVisibility(View.VISIBLE);
            underline2.setVisibility(View.INVISIBLE);
        }
    }

    public void clickButton7(View view){
        Button but = (Button)findViewById(R.id.button7);
        if (this.currentPlayer == 1) {
            but.setBackgroundResource(R.drawable.crossttt);
        }
        else {
            but.setBackgroundResource(R.drawable.circlettt);
        }

        this.myGame.move(this.currentPlayer,2,0);

        if(this.myGame.isFinished) {
            throwAlert(view);
        }

        this.currentPlayer = (this.currentPlayer)%2 + 1;
        but.setEnabled(false);
        ImageView underline1 = (ImageView)findViewById(R.id.underlinepl1);
        ImageView underline2 = (ImageView)findViewById(R.id.underlinepl2);
        if (underline1.getVisibility() == View.VISIBLE){
            underline1.setVisibility(View.INVISIBLE);
            underline2.setVisibility(View.VISIBLE);
        }
        else {
            underline1.setVisibility(View.VISIBLE);
            underline2.setVisibility(View.INVISIBLE);
        }
    }

    public void clickButton8(View view){
        Button but = (Button)findViewById(R.id.button8);
        if (this.currentPlayer == 1) {
            but.setBackgroundResource(R.drawable.crossttt);
        }
        else {
            but.setBackgroundResource(R.drawable.circlettt);
        }

        this.myGame.move(this.currentPlayer,2,1);

        if(this.myGame.isFinished) {
            throwAlert(view);
        }

        this.currentPlayer = (this.currentPlayer)%2 + 1;
        but.setEnabled(false);
        ImageView underline1 = (ImageView)findViewById(R.id.underlinepl1);
        ImageView underline2 = (ImageView)findViewById(R.id.underlinepl2);
        if (underline1.getVisibility() == View.VISIBLE){
            underline1.setVisibility(View.INVISIBLE);
            underline2.setVisibility(View.VISIBLE);
        }
        else {
            underline1.setVisibility(View.VISIBLE);
            underline2.setVisibility(View.INVISIBLE);
        }
    }

    public void clickButton9(View view){
        Button but = (Button)findViewById(R.id.button9);
        if (this.currentPlayer == 1) {
            but.setBackgroundResource(R.drawable.crossttt);
        }
        else {
            but.setBackgroundResource(R.drawable.circlettt);
        }

        this.myGame.move(this.currentPlayer,2,2);

        if(this.myGame.isFinished) {
            throwAlert(view);
        }

        this.currentPlayer = (this.currentPlayer)%2 + 1;
        but.setEnabled(false);
        ImageView underline1 = (ImageView)findViewById(R.id.underlinepl1);
        ImageView underline2 = (ImageView)findViewById(R.id.underlinepl2);
        if (underline1.getVisibility() == View.VISIBLE){
            underline1.setVisibility(View.INVISIBLE);
            underline2.setVisibility(View.VISIBLE);
        }
        else {
            underline1.setVisibility(View.VISIBLE);
            underline2.setVisibility(View.INVISIBLE);
        }
    }

    public void throwAlert(View view) {
        AlertDialog.Builder endGame = new AlertDialog.Builder(this);
        int winner = this.myGame.getWinner();
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

        int strtr = this.myGame.starter;
        this.myGame = new TicTacToeGame();
        this.myGame.starter = (strtr)%2 + 1;
        this.myGame.setNextPlayer(this.myGame.starter);

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

        Button but1 = (Button)findViewById(R.id.button1);
        but1.setEnabled(true);
        but1.setBackgroundResource(R.drawable.empty);

        Button but2 = (Button)findViewById(R.id.button2);
        but2.setEnabled(true);
        but2.setBackgroundResource(R.drawable.empty);

        Button but3 = (Button)findViewById(R.id.button3);
        but3.setEnabled(true);
        but3.setBackgroundResource(R.drawable.empty);

        Button but4 = (Button)findViewById(R.id.button4);
        but4.setEnabled(true);
        but4.setBackgroundResource(R.drawable.empty);

        Button but5 = (Button)findViewById(R.id.button5);
        but5.setEnabled(true);
        but5.setBackgroundResource(R.drawable.empty);

        Button but6 = (Button)findViewById(R.id.button6);
        but6.setEnabled(true);
        but6.setBackgroundResource(R.drawable.empty);

        Button but7 = (Button)findViewById(R.id.button7);
        but7.setEnabled(true);
        but7.setBackgroundResource(R.drawable.empty);

        Button but8 = (Button)findViewById(R.id.button8);
        but8.setEnabled(true);
        but8.setBackgroundResource(R.drawable.empty);

        Button but9 = (Button)findViewById(R.id.button9);
        but9.setEnabled(true);
        but9.setBackgroundResource(R.drawable.empty);
    }


}
