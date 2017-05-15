package com.example.hugomartinet.boardgames;

/**
 * Created by hugomartinet on 15/05/2017.
 */

public class TicTacToeGame {
    private int numberOfPlayers;
    private int nextPlayer;
    private int[][] grid;
    private boolean isFinished;
    private int winner;

    public TicTacToeGame(int numberOfPlayers){
        this.numberOfPlayers = numberOfPlayers;
        this.nextPlayer = 1;
        this.grid = new int[3][3];
        this.isFinished = false;
    }

    public TicTacToeGame(int numberOfPlayers, int nextPlayer){
        this.numberOfPlayers = numberOfPlayers;
        this.nextPlayer = nextPlayer;
        this.grid = new int[3][3];
        this.isFinished = false;
    }

    public void setNumberOfPlayers(int nb){
        this.numberOfPlayers = nb;
    }

    public void changeNextPlayer(int nb){
        this.nextPlayer = (this.nextPlayer)%2 + 1;
    }

    private boolean check(int playerNb){
        boolean win = false;
        if (this.grid[1][1] == playerNb){
            if (this.grid[0][1] == playerNb && this.grid[2][1] == playerNb){
                win = true;
            }
            else if (this.grid[0][0] == playerNb && this.grid[2][2] == playerNb){
                win = true;
            }
            else if (this.grid[2][0] == playerNb && this.grid[0][2] == playerNb){
                win = true;
            }
            else if (this.grid[1][0] == playerNb && this.grid[1][2] == playerNb){
                win = true;
            }
        } else {
            if (this.grid[0][0] == playerNb && this.grid[0][1] == playerNb && this.grid[0][2] == playerNb) {
                    win = true;
            } else if (this.grid[0][0] == playerNb && this.grid[1][0] == playerNb && this.grid[2][0] == playerNb) {
                    win = true;
            } else if (this.grid[2][0] == playerNb && this.grid[2][1] == playerNb && this.grid[2][2] == playerNb) {
                    win = true;
            } else if (this.grid[0][2] == playerNb && this.grid[1][2] == playerNb && this.grid[2][2] == playerNb) {
                    win = true;
            }
        }
        return win;
    }

    public void move(int player, int row, int col){
        this.grid[row][col] = player;
        if (check(1)) {
            this.isFinished = true;
            this.winner = 1;
        } else if (check(2)) {
            this.isFinished = true;
            this.winner = 2;
        }
    }
}
