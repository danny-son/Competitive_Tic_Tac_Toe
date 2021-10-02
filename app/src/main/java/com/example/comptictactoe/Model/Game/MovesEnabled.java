package com.example.comptictactoe.Model.Game;


/**
 * Class that handles whether the move was enabled or not
 */
public class MovesEnabled {
    private boolean delete;
    private boolean swap;
    private boolean place;
    private boolean endTurn;
    private boolean extraTurn;

    public MovesEnabled() {
        this.delete = false;
        this.swap = false;
        this.place = false;
        this.endTurn = false;
        this.extraTurn = false;
    }

    public boolean getDelete() {
        return this.delete;
    }

    public boolean getSwap() {
        return this.swap;
    }

    public boolean getPlace() {
        return this.place;
    }

    public boolean getEndTurn() {
        return this.endTurn;
    }

    public boolean getExtraTurn() {
        return this.extraTurn;
    }


    public void setExtraTurn(boolean isEnabled) {
        this.extraTurn = isEnabled;
    }

    public void setDelete(boolean isEnabled) {
        this.delete = isEnabled;
    }

    public void setSwap(boolean isEnabled) {
        this.swap = isEnabled;
    }

    public void setPlace(boolean isEnabled) {
        this.place = isEnabled;
    }

    public void setEndTurn(boolean isEnabled) {
        this.endTurn = isEnabled;
    }

    public void resetMove() {
        this.endTurn = false;
        this.extraTurn = false;
        this.place = false;
        this.swap = false;
        this.delete = false;
    }

    public boolean turnUsed() {
        return (this.place || this.swap || this.delete);
    }
}
