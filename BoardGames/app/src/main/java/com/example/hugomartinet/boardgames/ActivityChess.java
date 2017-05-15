package com.example.hugomartinet.boardgames;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityChess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess);

        TextView tx = (TextView)findViewById(R.id.titleChess);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/introinline.otf");
        tx.setTypeface(custom_font);
    }
}
