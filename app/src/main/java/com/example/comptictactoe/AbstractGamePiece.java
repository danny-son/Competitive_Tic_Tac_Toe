package com.example.comptictactoe;

//abstract class for determining our GamePiece
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
