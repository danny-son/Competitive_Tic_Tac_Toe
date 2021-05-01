package com.example.comptictactoe.ViewModel;

import android.view.View;
import android.widget.Button;

import java.util.HashMap;

/**
 * Interface to Represent our View Model
 * Handles Methods upon user interaction from our view
 */
public interface IViewModel {

    //allows the players to input text in a button

    /**
     * Allows the user to input text
     * @param v View/Button we want to input text to
     * @param a String to represent the text
     */
    public void InputText(View v, String a);

    //turns to a page of a given string to match with the activity

    /**
     * Allows the User to transition to the given Activity
     * @param v View/Button we want this function to work on
     * @param s String to represent the activity we want to switch to
     */
    public void nextPage(View v, String s);

    /**
     * Creates our HashMap of buttons
     * @param buttonMap HashMap of our button that represents our grid
     * @param buttonMapDup Duplicate HashMap of our button that represents our grid
     * @param buttonMapMoves HashMap of our moves the user can choose from
     */
    public void createButtonMap(HashMap<Button,Integer> buttonMap,
                                HashMap<Button,Integer> buttonMapDup,
                                HashMap<Button,Integer> buttonMapMoves);

    /**
     * Allows the User to place a Piece
     * @param v View/Button we want this method to work on
     */
    public void placePiece(View v);
}
