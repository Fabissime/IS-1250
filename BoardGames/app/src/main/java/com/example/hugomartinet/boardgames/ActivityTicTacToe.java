package com.example.hugomartinet.boardgames;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class ActivityTicTacToe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        TextView tx = (TextView) findViewById(R.id.titleTicTacToe);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/introinline.otf");
        tx.setTypeface(custom_font);

        Button startButton = (Button)findViewById(R.id.buttonStart);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityTicTacToe.this, ActivityGameTicTacToe.class));
            }
        });
    }

    public void onClick1(View view){
        EditText editT2 = (EditText)findViewById(R.id.editText2);
        editT2.setText("");
        editT2.setEnabled(true);
    }

    public void onClick2(View view){
        EditText editT2 = (EditText)findViewById(R.id.editText2);
        editT2.setText("Computer");
        editT2.setEnabled(false);
    }
}
