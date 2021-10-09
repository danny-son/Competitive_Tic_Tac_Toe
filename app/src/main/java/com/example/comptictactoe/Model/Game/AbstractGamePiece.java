package com.example.comptictactoe.Model.Game;

/**
 * Abstract Class for GamePiece
 */
abstract class AbstractGamePiece implements GamePiece {

    @Override
    public boolean isEmptyGamePiece() {
        return false;
    }

    @Override
    public boolean isOGamePiece() {
        return false;
    }

    @Override
    public boolean isXGamePiece(){
        return false;
    }


}
