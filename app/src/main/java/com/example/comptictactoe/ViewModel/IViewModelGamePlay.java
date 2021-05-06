package com.example.comptictactoe.ViewModel;

import android.view.View;
import android.widget.Button;

import com.example.comptictactoe.Model.Player;

import java.util.HashMap;

/**
 * Interface to Represent our View Model
 * Handles Methods upon user interaction from our view
 */
public interface IViewModelGamePlay {

    /**
     * Creates our HashMap of buttons
     *  1. Creates a HashMap of buttons that represent our grid
     *      (used for when players want to place a piece)
     *  2. Creates a duplicate HashMap of buttons for players to perform more functionality
     *      (used for when players want to move or delete a piece)
     *  3. Creates a HashMap of our buttons that we can use to perform certain moves
     *     (Ex -> Place, Swap, Delete, End Turn, Extra Turn, Increase Grid, Randomize)
     */
    void createButtonMap();

    /**
     * Sets up the move for the player to place the piece, makes buttons visible to place piece
     *  - edits text to show what move was performed
     * @param v View/Button we want this method to work on
     */
    void placePieceInit(View v);

    /**
     * Performs the aciton of a Player placing a piece when selected a button on the grid
     * @param v View/Button we want this method to work on
     *          (our grid, once button ("Place" was selected)
     */
    void placePiece(View v);

    /**
     * Helper method to perform placePiece
     * looks in our TicTacToeModel to perform makeMove method
     * @param player Player we want to place their piece
     * @param row row index where we want to place our piece
     * @param col column index where want to place our piece
     */
    void placePieceHelper(Player player, int row, int col);

    /**
     * Determines if the player has won the game, if so sets the winning game text, and disables
     * every button to prevent further action.
     * @param player Player we want to check if their gamePieces on the grid
     *               fits the game winning state
     */
    void didPlayerWin(Player player);

    /**
     * Updates our Buttons in our game Grid to have text in each button that determine what
     * GamePiece that button holds ("", "X" or "O")
     * @param map our HashMap of buttons we want to update tur Text
     */
    void updateButtonsText(HashMap<Button, Integer> map);

    /**
     * Changes our HashMap of Buttons click status
     * @param map HashMap of Buttons we want to change its click status
     * @param clickable Boolean value to determine if we want the buttons to be clickable or not
     */
    void setButtonClickable(HashMap<Button, Integer> map, boolean clickable);

    /**
     * Changes our buttons on the grid Click stauts, based on whether we want to set empty pieces
     * or non empty pieces to be clickable
     * @param map HashMap of Buttons we want to change its click status
     * @param emptyPieces Boolean value to determine if we want emptyPieces to be clickable or
     *                    non empty pieces to be clickable
     */
    void setGamePieceClickable(HashMap<Button, Integer> map, boolean emptyPieces);

    /**
     * Sets all buttons in our given map to be enabled or not(both visible and clickable)
     * @param map Our HashMap of buttons we want to enable or disable
     * @param enable Boolean value to determine whether we want to enable our buttons or not
     */
    void enableOrDisableAllButtons(HashMap<Button, Integer> map, boolean enable);

    /**
     * Edit's text to show who's turn it is
     */
    void changeTurnView();

    /**
     * Ends the turn in our game, sets our buttons of moves to be clickable again
     * @param v View/Button we want to perform this action on
     */
    void endTurnView(View v);

    /**
     * Edit's a game Piece whether the player's Intention is to perform a swap or delete.
     * @param v View/Button we want to perform this action on
     */
    void editPiece(View v);

    /**
     * updates and resets the visibility and clicks of our buttons back to our non-dup buttons
     * resets our values "delete", "buttonSelect", "buttonMove" back to default
     */
    void resetMovesButtons();

    /**
     * The initial step when the Player decides to perform a move that edits our grid
     * excluding "Place"
     * Shows text corresponding to the button clicked on
     * @param v View/Button we want to perform this action on
     */
    void editPieceInit(View v);


    /**
     * Allows the player to perform an extra move,
     * Resets the buttons click status back to clickable except for extra turn.
     * @param v View/Button we want to perform this action on
     */
    void addTurnView(View v);




}
