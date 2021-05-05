package com.example.comptictactoe.ViewModel;

import android.view.View;

/**
 * ViewModel for the Instructions Page, includes functionality
 * to go back to the previous or next page
 */
public interface IViewModelInstructions {
    /**
     * Allows the User to transition to the given Activity
     *  - note provide switch statement on the types of pages we want to transition to based on
     *  - the button clicked
     * @param v View/Button we want this function to work on
     *
     */
    void nextPage(View v);
}
