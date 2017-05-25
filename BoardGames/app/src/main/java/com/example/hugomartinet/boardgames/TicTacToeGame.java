package com.example.hugomartinet.boardgames;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hugomartinet on 15/05/2017.
 */

public class TicTacToeGame {
    private int numberOfPlayers;
    public int[][] grid;
    private int computerLevel;
    public boolean isFinished;
    public int starter;
    private int winner;


    public TicTacToeGame(){
        this.grid = new int[3][3];
        this.isFinished = false;
    }



    public void setNumberOfPlayers(int nb){this.numberOfPlayers = nb;}

    public int getNumberOfPlayers(){ return this.numberOfPlayers;}

    public void setComputerLevel(int lvl) {this.computerLevel = lvl;}

    public int getComputerLevel() {return this.computerLevel;}

    public int getWinner(){return this.winner;}

    private boolean check(int[][] grid, int nb){
        if (grid[0][0] == nb && grid[1][0] == nb && grid[2][0] == nb) {return true;}
        else if (grid[0][1] == nb && grid[1][1] == nb && grid[2][1] == nb) {return true;}
        else if (grid[0][2] == nb && grid[1][2] == nb && grid[2][2] == nb) {return true;}
        else if (grid[0][0] == nb && grid[0][1] == nb && grid[0][2] == nb) {return true;}
        else if (grid[1][0] == nb && grid[1][1] == nb && grid[1][2] == nb) {return true;}
        else if (grid[2][0] == nb && grid[2][1] == nb && grid[2][2] == nb) {return true;}
        else if (grid[0][0] == nb && grid[1][1] == nb && grid[2][2] == nb) {return true;}
        else if (grid[0][2] == nb && grid[1][1] == nb && grid[2][0] == nb) {return true;}
        else {return false;}
    }

    private boolean isWinningMove(int[][] grid, int playerNb, int row, int col) {
        grid[row][col] = playerNb;
        return check(grid,playerNb);
    }

    private int[] winningPossibility(int[][] grid, int playerNb){
        for (int i = 0; i <3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                int[][] myCopy = clone(grid);
                if (myCopy[i][j] == 0){
                    myCopy[i][j] = playerNb;
                    if (check(myCopy,playerNb)){
                        return new int[] {i,j};
                    }
                }
            }
        }
        return null;
    }


    public int[][] clone(int[][] grid){
        int[][] copy = new int[3][3];
        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                copy[i][j] = grid[i][j];
            }
        }
        return copy;
    }

    public int[] computerMove() {

        ArrayList<int[]> available = new ArrayList<int[]>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (this.grid[row][col] == 0) {
                    available.add(new int[]{row, col});
                }
            }
        }

        int[] choice = null;

        switch (this.computerLevel){

            case 1 : choice = winningPossibility(this.grid, 2);
                if (choice == null) {
                    Collections.shuffle(available);
                    choice = available.get(0);
                };
                break;

            case 2 : choice = winningPossibility(this.grid, 2);
                if (choice == null) {
                    choice = winningPossibility(this.grid, 1);
                    if (choice == null){
                        Collections.shuffle(available);
                        choice = available.get(0);
                    }
                };
                break;

            case 3 : choice = winningPossibility(this.grid, 2);
                if (choice == null) {
                    choice = winningPossibility(this.grid, 1);
                    if (choice == null){
                        for(int[] pos: available){
                            int[][] myCopy = clone(this.grid);
                            myCopy[pos[0]][pos[1]] = 2;
                            if (winningPossibility(myCopy,2) != null){
                                choice = pos;
                                break;
                            }
                        }
                        if(choice == null){
                            Collections.shuffle(available);
                            choice = available.get(0);
                        }
                    }
                };
                break;

            default : break;

        }

        this.move(2,choice[0],choice[1]);
        return choice;
    }



    public void move(int player, int row, int col){
        this.grid[row][col] = player;

        boolean finished = true;
        for (int i = 0;i <3 ; i++){
            for(int j = 0; j < 3 ; j++) {
                if (grid[i][j] == 0){
                    finished = false;
                }
            }
        }
        if (check(this.grid,player)) {
            this.isFinished = true;
            this.winner = player;
        }
        else if (finished) {
            this.isFinished = true;
            this.winner = 0;
        }

    }



}
