package com.example.comptictactoe.Model;

import com.example.comptictactoe.Model.AbstractGamePiece;

/**
 * Empty GamePiece Class, inherits from AbstractGamePiece
 */
public class EmptyGP  extends AbstractGamePiece {
    @Override
    public boolean isEmptyGamePiece() {
        return true;
    }
}
