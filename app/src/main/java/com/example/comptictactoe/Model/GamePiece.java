package com.example.comptictactoe.Model;

public interface GamePiece {

    /**
     * Determines if the GamePiece is ' '
     * @return boolean
     */
    boolean isEmptyGamePiece();

    /**
     * Determines if the GamePiece is 'O'
     * @return boolean
     */
    boolean isOGamePiece();

    /**
     * Determines if the GamePiece is 'X'
     * @return boolean
     */
    boolean isXGamePiece();
}
