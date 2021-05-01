package com.example.comptictactoe.Model;

import com.example.comptictactoe.Model.AbstractGamePiece;

/**
 * Class to Resemble 'X' GamePiece, Inherits from AbstractGamePiece
 */
public class X extends AbstractGamePiece {
    @Override
    public boolean isXGamePiece() {
        return true;
    }
}
