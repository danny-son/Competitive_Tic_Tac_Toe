package com.example.comptictactoe.ViewModel;

import android.view.View;

/**
 * Interface to represent the main title screen for the player,
 * allows them to enter their names, their
 */
public interface IViewModelMain {


    /**
     * Allows the user to play the game
     * @param s1 name of player One
     * @param s2 name of player Two
     */
    public void navigateToGame(String s1, String s2);



}
