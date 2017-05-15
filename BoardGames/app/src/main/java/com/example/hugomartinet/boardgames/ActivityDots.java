package com.example.hugomartinet.boardgames;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityDots extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dots);

        TextView tx = (TextView)findViewById(R.id.titleDots);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/introinline.otf");
        tx.setTypeface(custom_font);
    }
}
