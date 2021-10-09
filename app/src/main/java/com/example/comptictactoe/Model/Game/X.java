package com.example.comptictactoe.Model.Game;

/**
 * Class to Resemble 'X' GamePiece, Inherits from AbstractGamePiece
 */
public class X extends AbstractGamePiece {
    @Override
    public boolean isXGamePiece() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof X;
    }
}
