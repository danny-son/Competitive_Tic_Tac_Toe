package com.example.comptictactoe.Model;

import java.util.ArrayList;

/**
 * Model For TicTacToe
 * - Handles Game Logic
 */
public interface TicTacToeModel {
    /**
     * Creates a 2D grid for our game
     * @param row the number of rows for our grid
     * @param column the number of columns for our grid
     * @return a 2D Arraylist of GamePieces
     */
    ArrayList<ArrayList<GamePiece>> createGrid(int row, int column);

    /**
     * Determines if the game is over, by having a player win the game
     *  - Determined by if there is the same game piece (X or O) in the same row, column, or diagonal direction
     * @param player The player we are checking to see if they fit the requirements to win
     * @return Boolean to determine if the player won
     */
    boolean isGameOver(Player player);

    /**
     * Makes a move for the player, places the player's game piece
     * @param player Player that wants to place the piece
     * @param row Row Index to place the piece
     * @param column Column Index to place the piece
     */
    void makeMove(Player player, int row, int column);


    /**
     * Swaps the Current Game Piece to another location
     * @param rowBefore row index before
     * @param colBefore column index before
     * @param rowAfter row index after
     * @param colAfter column index after
     */
    void swapPieces(int rowBefore, int colBefore, int rowAfter, int colAfter);

    /**
     * Deletes a GamePiece on the board, changes it to empty
     * @param row row index we want to delete
     * @param col column index we want to delete
     */
    void deletePiece(int row, int col);

    /**
     * Ends the current player's turn, swaps turns
     * @param p1 player one
     * @param p2 player two
     */
    void endTurn(Player p1, Player p2);

    /**
     *
     * @return a 2d Array of GamePieces
     */
    ArrayList<ArrayList<GamePiece>> getGrid();

    /**
     * Increases our grid size (if 3x3 -> 5x5 -> 7x7)
     */
    void increaseGrid();


    /**
     * Sets our grid
     * @param grid The grid we want our gameboard to represent
     */
    void setGrid(ArrayList<ArrayList<GamePiece>> grid);

    /**
     * Gets the player we want
     * @param playerOne boolean to determine if its player one or not
     * @return our Player
     */
    Player getPlayer(boolean playerOne);

    /**
     *
     * @return an Int that represents the number of turns passed in the game.
     */
    int getTurnNumber();


}
