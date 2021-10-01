package com.example.comptictactoe.Model;

import androidx.annotation.Nullable;

import com.example.comptictactoe.Model.AbstractGamePiece;

/**
 * Class to Resemble 'O' GamePiece, Inherits from AbstractGamePiece
 */
public class O extends AbstractGamePiece {
    @Override
    public boolean isOGamePiece() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof O;
    }


}
