package com.example.hugomartinet.boardgames;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by hugomartinet on 04/06/2017.
 */

public class DotsBoard extends View {

    public DotsGame delegate;

    public DotsBoard(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        ArrayList<Edge> edges = delegate.getEdges();
        ArrayList<Rectangle> rectangles = delegate.getRectangles();

        for (Edge e : edges){
            if(e.getPlayer() == 1){paint.setColor(Color.parseColor("#1EBEFF"));}
            else{paint.setColor(Color.parseColor("#FF9D19"));}

            int[] start = e.getStart();
            int[] end = e.getEnd();
            canvas.drawRect(9+174*start[1],9+125*start[0],21+174*end[1],21+125*end[0],paint);
        }

        for (Rectangle r : rectangles){
            int id;

            if(r.getPlayer() == 1){id = R.drawable.bluerect;}
            else{id = R.drawable.orangerect;}

            int row = r.getRow();
            int col = r.getCol();

            Drawable d = getResources().getDrawable(id);
            d.setBounds(23 + 174*col, 23 + 125*row, 181 + 174*col, 132 + 125*row);
            d.draw(canvas);
        }

        paint.setColor(Color.WHITE);

        int r = 15;

        for(int i = 0; i < 6 ; i++){
            int x = 15+ i*174;
            for(int j = 0; j< 8 ; j++){
                int y = 15 + j*125;
                canvas.drawCircle(x,y,r,paint);
            }
        }
    }

}
