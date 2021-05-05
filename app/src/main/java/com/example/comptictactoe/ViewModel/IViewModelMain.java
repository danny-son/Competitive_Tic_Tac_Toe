package com.example.comptictactoe.ViewModel;

import android.view.View;

/**
 * Interface to represent the main title screen for the player,
 * allows them to enter their names, their
 */
public interface IViewModelMain {

    /**
     * Allows the user to input text
     * @param v View/Button we want to input text to
     */
    void inputText(View v);



    /**
     * Allows the User to transition to the given Activity
     *  - note provide switch statement on the types of pages we want to transition to based on
     *  - the button clicked
     * @param v View/Button we want this function to work on
     *
     */
    void nextPage(View v);



}
