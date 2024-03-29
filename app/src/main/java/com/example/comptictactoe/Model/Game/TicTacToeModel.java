package com.example.comptictactoe.Model.Game;

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
     * @param rowAfter row index after
     * @param colAfter column index after
     */
    void swapPieces(Player p, int rowAfter, int colAfter);

    /**
     * Deletes a GamePiece on the board, changes it to empty
     * @param row row index we want to delete
     * @param col column index we want to delete
     */
    void deletePiece(Player p, int row, int col);

    /**
     * Ends the current player's turn, swaps turns
     */
    void endTurn();


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
     * Allows the player to gain an extra turn
     * resets their turn use, handles cost and increment of extra turn
     * @param p Player we want to perform this action on.
     */
    void gainExtraTurn(Player p);

    /**
     * Method to determine how many points the player can accumulate after their turn ends
     * - done by counting every player's GamePiece that are adjacent to each other.
     * @param p Player we are using its GamePiece and checking for possible points
     * @return integer that represents every adjacent pair we find
     */
    int accumulatePoints(Player p);


}
