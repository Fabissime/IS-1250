package com.example.hugomartinet.boardgames;

/**
 * Created by hugomartinet on 04/06/2017.
 */

public class Edge {
    private int[] start;
    private int[] end;
    private int player;


    public Edge(int[] start, int[] end, int player){
        this.start = start;
        this.end = end;
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

    public int[] getStart() {
        return start;
    }

    public int[] getEnd() {
        return end;
    }

    public boolean isSameEdge(int[] start, int[] end){
        if(this.start[0] == start[0] && this.end[0] == end[0] && this.start[1] == start[1] && this.end[1] == end[1]){
            return true;
        }
        if(this.start[0] == end[0] && this.end[0] == start[0]  && this.start[1] == end[1] && this.end[1] == start[1]){
            return true;
        }
        return false;
    }

}
