package com.example.hugomartinet.boardgames;

/**
 * Created by hugomartinet on 04/06/2017.
 */

public class Rectangle {
    private int row;
    private int col;
    private int player;

    public Rectangle(int row, int col, int player){
        this.row = row;
        this.col = col;
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getPlayer() {
        return player;
    }
}
