package com.example.comptictactoe;

import java.util.ArrayList;

//model for TicTacToe
public interface TicTacToeModel {
    //creates a grid of our game
    ArrayList<ArrayList<GamePiece>> createGrid(int row, int column);

    //determines if the game is over by way of win conditions for that player
    boolean isGameOver(Player player);

    //makes a move to place an 'X' or 'O'
    void makeMove(Player p, int row, int column);

    //increases the size of the grid by 2 on row and column
    void increaseGrid();


    //moves the current moves made relative to when we increase the size of the grid
    void moveCurrentGP(int rowBefore, int colBefore, int rowAfter, int colAfter);

    //ends the players turn, and makes the other players turn valid
    void endTurn(Player p1, Player p2);

    //gets the model's Grid
    ArrayList<ArrayList<GamePiece>> getGrid();

    //sets the model's Grid
    void setGrid(ArrayList<ArrayList<GamePiece>> newGrid);

    //gets the Player in our game
    Player getPlayer(boolean playerOne);

}
