package com.example.hugomartinet.boardgames;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hugomartinet on 02/06/2017.
 */

public class FourInARowGame {

    private int numberOfPlayers;
    private int computerLevel;
    private boolean isFinished;
    private int starter;
    private int winner;
    private int[][] grid;


    public FourInARowGame() {
        this.isFinished = false;
        this.grid = new int[7][7];
    }


    public boolean columnFilled(int[][] grid, int column){
        return (grid[column][0] != 0);
    }

    public int getLastRow(int col){
        int row = 0;
        while(row < 6 && this.grid[col][row] == 0){
            row++;
        }
        return row;
    }

    public boolean gameFinished(){
        return this.isFinished;
    }

    public int getWinner() {
        return winner;
    }

    public int getStarter() {
        return starter;
    }

    public void setStarter(int starter) {
        this.starter = starter;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getComputerLevel() {
        return computerLevel;
    }

    public void setComputerLevel(int computerLevel) {
        this.computerLevel = computerLevel;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void move(int player, int column){
        int row = 0;
        while (row < 7 && this.grid[column][row] == 0){
            row++ ;
        }
        this.grid[column][row-1] = player;
        if (isWinningMove(this.grid, player)){
            this.isFinished = true;
            this.winner = player;
        }
    }

    public int computerMove(){

        ArrayList<Integer> available = new ArrayList<Integer>();
        for(int i = 0 ; i < 7 ; i++){
            if(!columnFilled(this.grid, i)){
                available.add(i);
            }
        }


        int choice = -1;

        switch(computerLevel){

            case 1 : choice = winningPossibility(this.grid, 2);
                if (choice == -1){
                    Collections.shuffle(available);
                    choice = available.get(0);
                }
                break;

            case 2 :
                choice = winningPossibility(this.grid, 2);
                if (choice == -1){
                    choice = winningPossibility(this.grid, 1);
                    System.out.println(choice);
                    if (choice == -1){
                        Collections.shuffle(available);
                        choice = available.get(0);
                    }
                }
                break;

            case 3 : choice = winningPossibility(this.grid, 2);
                if (choice == -1){
                    choice = winningPossibility(this.grid, 1);
                    if (choice == -1){
                        ArrayList<Integer> available2 = new ArrayList<Integer>();
                        for(int i : available){
                            available2.add(i);
                        }
                        for(int i : available2){
                            int[][] copy = clone(this.grid);
                            addChip(copy, i, 2);
                            if(winningPossibility(copy, 1) != -1){
                                available.remove(i);
                            }
                        }
                        for (int i : available){
                            int[][] copy2 = clone(this.grid);
                            addChip(copy2, i , 2);
                            if (winningPossibility(copy2, 2) != -1){
                                choice = i;
                                break;
                            }
                        }
                        Collections.shuffle(available);
                        choice = available.get(0);
                    }
                }
                break;


            default : break;
        }
        this.move(2,choice);
        return choice;
    }

    public void addChip(int[][] grid, int col, int player){
        int row = 6;
        while(row > 0 && grid[col][row] != 0){
            row--;
        }
        grid[col][row] = player;
    }

    public int[][] clone(int[][] grid){
        int[][] myClone = new int[7][7];
        for (int i = 0 ; i < 7 ; i++){
            for (int j = 0 ; j < 7 ; j++){
                myClone[i][j] = grid[i][j];
            }
        }
        return myClone;
    }

    public int winningPossibility(int[][] grid, int player){
        for (int i = 0 ; i < 7 ; i++){
            int[][] copy = clone(grid);
            if(!columnFilled(copy, i)){
                addChip(copy,i,player);
                if (isWinningMove(copy, player)){
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean isWinningMove(int[][] grid, int player) {
        ArrayList<int[]> chips = new ArrayList<int[]>();
        for (int i = 0; i < 7 ; i++){
            for (int j = 0; j<7 ; j++){
                if (grid[i][j] == player){
                    chips.add(new int[]{i, j});
                }
            }
        }
        for (int[] couple:chips){
            int col = couple[0];
            int row = couple[1];
            if(col < 4 && row < 4 && grid[col+1][row+1] == player && grid[col+2][row+2] == player && grid[col+3][row+3] == player){
                return true;
            }
            if(row < 4 && grid[col][row+1] == player && grid[col][row+2] == player && grid[col][row+3] == player){
                return true;
            }
            if(col < 4 && grid[col+1][row] == player && grid[col+2][row] == player && grid[col+3][row] == player){
                return true;
            }
            if(col < 4 && row > 2 && grid[col+1][row-1] == player && grid[col+2][row-2] == player && grid[col+3][row-3] == player){
                return true;
            }
        }
        return false;
    }
}
