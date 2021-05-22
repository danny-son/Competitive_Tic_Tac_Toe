package com.example.comptictactoe.ViewModel;

import android.view.View;
import android.widget.Button;

import com.example.comptictactoe.Model.Player;

import java.util.ArrayList;
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
     *  2. Creates a HashMap of our buttons that we can use to perform certain moves
     *     (Ex -> Place, Swap, Delete, End Turn, Extra Turn, Increase Grid, Randomize)
     * @param buttonList ArrayList of buttons that we want to add in our map, and also re assign
     *                   our button map
     */
    void createButtonMap(ArrayList<Button> buttonList);

    /**
     * Removes our grid of buttons from the gameplayActivity
     */
    void removeButtonsFromActivity();

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
     * updates and resets the visibility and clicks of our buttons back to our non-dup buttons
     * resets our values "delete", "buttonSelect", "buttonMove" back to default
     */
    void resetMovesButtons();

    /**
     * The initial step when the Player decides to perform a move that swaps gamePieces
     * Shows text corresponding to the button clicked on
     * @param v View/Button we want to perform this action on
     */
    void swapPieceInit(View v);


    /**
     * Allows the player to perform an extra move,
     * Resets the buttons click status back to clickable except for extra turn.
     * @param v View/Button we want to perform this action on
     */
    void addTurnView(View v);


    /**
     * Increases the grid size, Performs many steps
     *  1. increases our gameGrid (2D array of gamePieces)
     *  2. alters our gameGrid previously to fit with the new one (keep pieces in the center)
     *  3. recreates our buttonMap and buttonMapDuplicate, and creates the amount that represents
     *      our gridSize
     *  4. relocates our buttonMap and buttonMapDuplicate respectively,
     *      change size if buttons overlap each other or overlaps screen
     *  5. resets the number of turns to perform increase grid again
     *
     */
    void dynamicallyIncreaseGrid(View v);


    /**
     * Dynamically creates our list of buttons that represents the grid
     * @param gridSize the amount of buttons we want to create based on the grid size
     * @return an ArrayList of Buttons
     */
    ArrayList<Button> createButtons(int gridSize);

    /**
     * Allows the button that is clicked to perform the actions based on the move the user
     * clicked on previously
     * @param v Button we want to perform this action on.
     */
    void selectMoves(View v);

    /**
     * Allows the button that is clicked to be deleted from the gameBoard, remains an
     * 'Empty GamePiece'
     * @param v Button we want to perform this action on.
     */
    void deletePiece(View v);

    /**
     * Allows the two button that is clicked to be swapped in the gameBoard
     * @param v Button we want to perform this action on.
     */
    void swapPiece(View v);

    /**
     * Initial stage setting up for the player to delete a gamePiece
     * @param v Button we want to perform this action on.
     */
    void deletePieceInit(View v);
}
