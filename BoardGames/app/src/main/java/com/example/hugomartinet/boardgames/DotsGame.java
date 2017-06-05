package com.example.hugomartinet.boardgames;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hugomartinet on 04/06/2017.
 */

class DotsGame {

    private ArrayList<Edge> edges;
    private ArrayList<Rectangle> rectangles;
    private int numberOfPlayers;
    private int computerLevel;
    private boolean isFinished;
    private int starter;
    private int winner;

    public DotsGame() {
        this.isFinished = false;
        this.edges = new ArrayList<Edge>();
        this.rectangles = new ArrayList<Rectangle>();
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }

    public int getStarter() {
        return starter;
    }

    public void setNumberOfPlayers(int nbOfPlayers) {
        this.numberOfPlayers = nbOfPlayers;
    }

    public void setComputerLevel(int computerLevel) {
        this.computerLevel = computerLevel;
    }

    public void setStarter(int starter) {
        this.starter = starter;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public boolean isFinished() {
        return isFinished;
    }


    public int addEdge(Edge e, ArrayList<Edge> edges, boolean realMove){
        edges.add(e);
        return checkScoringMove(e, edges, realMove);
    }

    public void checkEndOfGame() {
        if(this.rectangles.size() == 35){
            this.isFinished = true;
        }
    }


    public int checkScoringMove(Edge e, ArrayList<Edge> edges, boolean realMove){
        int[] start = e.getStart();
        int[] end = e.getEnd();
        int b1;
        int b2;
        if(end[0] == start[0]){
            b1 = checkRectangle(start[0]-1,start[1],e,edges, realMove);
            b2 = checkRectangle(start[0],start[1],e,edges, realMove);
        } else {
            b1 = checkRectangle(start[0],start[1]-1,e,edges, realMove);
            b2 = checkRectangle(start[0],start[1],e,edges, realMove);
        }
        return b1+b2;
    }


    public int checkRectangle(int row, int col, Edge e, ArrayList<Edge> edges, boolean realMove){
        if(!containsRect(row,col)) {
            if (contains(new int[]{row, col}, new int[]{row + 1, col}, edges) && contains(new int[]{row, col}, new int[]{row, col + 1}, edges) &&
                    contains(new int[]{row + 1, col}, new int[]{row + 1, col + 1}, edges) && contains(new int[]{row, col + 1}, new int[]{row + 1, col + 1}, edges)) {
                if(realMove){this.rectangles.add(new Rectangle(row,col,e.getPlayer()));}
                return 1;
            }
        }
        return 0;
    }


    public int computerMove(){
        Edge choice = null;

        ArrayList<Edge> available = new ArrayList<Edge>();
        for (int i = 0; i < 8 ; i++){
            for (int j = 0 ; j < 6; j ++){
                if (i < 7){
                    Edge e = new Edge(new int[] {i,j}, new int[] {i+1,j}, 2);
                    if(!contains(e.getStart(),e.getEnd(),this.edges)){available.add(e);}
                }
                if (j < 5){
                    Edge e = new Edge(new int[] {i,j}, new int[] {i,j+1}, 2);
                    if(!contains(e.getStart(),e.getEnd(),this.edges)){available.add(e);}
                }
            }
        }



        switch(computerLevel){

            case 1 : Collections.shuffle(available);
                choice = available.get(0);
                break;

            case 2 : choice = scoringPossibility(this.edges,available);
                if (choice == null){
                    Collections.shuffle(available);
                    choice = available.get(0);
                }
                break;

            case 3 : choice = scoringPossibility(this.edges,available);
                if (choice == null) {
                    ArrayList<Edge> availableCopy = clone(available);
                    for(Edge e : available){
                        ArrayList<Edge> copy = clone(this.edges);
                        addEdge(e,copy,false);
                        if(scoringPossibility(copy,available) != null){
                            availableCopy.remove(e);
                        }
                    }
                    System.out.println("("+available.size()+","+availableCopy.size()+")");
                    if(availableCopy.size() > 0) {
                        Collections.shuffle(availableCopy);
                        choice = availableCopy.get(0);
                    } else {
                        Collections.shuffle(available);
                        choice = available.get(0);
                    }
                }
                break;


            default : break;
        }
        return addEdge(choice, this.edges, true);
    }

    private Edge scoringPossibility(ArrayList<Edge> edges, ArrayList<Edge> available) {
        for(Edge e : available){
            ArrayList<Edge> copy = clone(edges);
            if(addEdge(e,copy,false) > 0){
                return e;
            }
        }
        return null;
    }

    public ArrayList<Edge> clone(ArrayList<Edge> edges){
        ArrayList<Edge> myClone = new ArrayList<Edge>();
        for(Edge e : edges){
            myClone.add(e);
        }
        return myClone;
    }

    private boolean containsRect(int row, int col) {
        for (Rectangle r: rectangles){
            if (r.getRow() == row && r.getCol() == col){
                return true;
            }
        }
        return false;
    }


    public boolean contains(int[] start, int[] end, ArrayList<Edge> edges){
        for (Edge e1 : edges){
            if (e1.isSameEdge(start, end)){
                return true;
            }
        }
        return false;
    }

    public int getWinner() {
        return winner;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getComputerLevel() {
        return computerLevel;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }

    public void setRectangles(ArrayList<Rectangle> rectangles) {
        this.rectangles = rectangles;
    }
}
