package com.example.comptictactoe.Model;


/**
 * Empty GamePiece Class, inherits from AbstractGamePiece
 */
public class EmptyGP  extends AbstractGamePiece {
    @Override
    public boolean isEmptyGamePiece() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof EmptyGP;
    }
}
