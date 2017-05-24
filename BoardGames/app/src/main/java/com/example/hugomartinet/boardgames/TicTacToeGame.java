package com.example.hugomartinet.boardgames;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hugomartinet on 15/05/2017.
 */

public class TicTacToeGame {
    private int numberOfPlayers;
    private int nextPlayer;
    private int[][] grid;
    private int computerLevel;
    public boolean isFinished;
    public int starter;
    private int winner;


    public TicTacToeGame(){
        this.grid = new int[3][3];
        this.isFinished = false;
    }



    public void setNumberOfPlayers(int nb){
        this.numberOfPlayers = nb;
    }

    public void changeNextPlayer(){
        this.nextPlayer = (this.nextPlayer)%2 + 1;
    }

    public int getNextPlayer(){
        return this.nextPlayer;
    }

    public void setNextPlayer(int next) {
        this.nextPlayer = next;
    }

    public void setComputerLevel(int lvl) {
        this.computerLevel = lvl;
    }

    public int getWinner(){
        return this.winner;
    }

    private boolean check(int[][] currentGrid, int playerNb){
        boolean win = false;
        if (currentGrid[1][1] == playerNb){
            if (currentGrid[0][1] == playerNb && currentGrid[2][1] == playerNb){
                win = true;
            }
            else if (currentGrid[0][0] == playerNb && currentGrid[2][2] == playerNb){
                win = true;
            }
            else if (currentGrid[2][0] == playerNb && currentGrid[0][2] == playerNb){
                win = true;
            }
            else if (currentGrid[1][0] == playerNb && currentGrid[1][2] == playerNb){
                win = true;
            }
        } else {
            if (currentGrid[0][0] == playerNb && currentGrid[0][1] == playerNb && currentGrid[0][2] == playerNb) {
                    win = true;
            } else if (currentGrid[0][0] == playerNb && currentGrid[1][0] == playerNb && currentGrid[2][0] == playerNb) {
                    win = true;
            } else if (currentGrid[2][0] == playerNb && currentGrid[2][1] == playerNb && currentGrid[2][2] == playerNb) {
                    win = true;
            } else if (currentGrid[0][2] == playerNb && currentGrid[1][2] == playerNb && currentGrid[2][2] == playerNb) {
                    win = true;
            }
        }
        return win;
    }

    private boolean isWinningMove(int[][] currentGrid, int playerNb, int row, int col) {
        int[][] myCopy = currentGrid.clone();
        myCopy[row][col] = playerNb;
        return check(myCopy,playerNb);
    }


    private int[] winningPossibility(int[][] currentGrid, int playerNb, ArrayList<int[]> available) {
        for (int i = 0; i < available.size(); i++){
            if (this.isWinningMove(currentGrid, playerNb, available.get(i)[0], available.get(i)[1])){
                return available.get(i);
            }
        }
        return null;
    }


    private void computerMove() {

        ArrayList<int[]> available = new ArrayList<int[]>();
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                if (this.grid[row][col] == 0){
                    available.add(new int[] {row,col});
                }
            }
        }

        int[] choice = null;

        if (this.computerLevel == 1) {
            Collections.shuffle(available);
            choice = available.get(0);
        }

        else if (this.computerLevel == 2){
            for(int i = 0; i < available.size();i++){
                choice = available.get(i);
                if(this.isWinningMove(this.grid, 2,choice[0],choice[1])){ break;}
            }
            choice = winningPossibility(this.grid, 1,available);
            if (choice == null){
                Collections.shuffle(available);
                choice = available.get(0);
            }
        }

        else {
            for(int i = 0; i < available.size();i++){
                choice = available.get(i);
                if(this.isWinningMove(this.grid, 2,choice[0],choice[1])){ break;}
            }
            choice = winningPossibility(this.grid, 1,available);
            if (choice == null){
                for (int i = 0; i < available.size(); i++){
                    int[][] myCopy = this.grid.clone();
                    myCopy[available.get(i)[0]][available.get(i)[1]] = 2;
                    ArrayList<int[]> availableCopy = (ArrayList<int[]>) available.clone();
                    if (winningPossibility(myCopy, 1,availableCopy) == null){
                        choice = available.get(i);
                        break;
                    }
                }
            }
        }
        this.grid[choice[0]][choice[1]] = 2;

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

        changeNextPlayer();
    }
}
