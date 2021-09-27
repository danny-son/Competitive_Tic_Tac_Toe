package com.example.comptictactoe.Model;


/**
 * Class that handles information regarding the points needed for each move,
 * Collaborates with Player class.
 */
public class MovesPoints {
    private final int place;
    private int swap;
    private int extraTurn;
    private int delete;

    /**
     * Main Constructor, initializes default cost for moves
     */
    MovesPoints() {
        this.place = 0;
        this.swap = 3;
        this.extraTurn = 5;
        this.delete = 2;
    }

    /**
     * Getter method
     * @return number representing the cost to place a gamePiece
     */
    public int getPlace() {
        return this.place;
    }

    /**
     * Getter method
     * @return number representing the cost to swap between two gamePieces
     */
    public int getSwap() {
        return this.swap;
    }

    /**
     * Getter method
     * @return number representing the cost to gain an extra turn
     */
    public int getExtraTurn() {
        return this.extraTurn;
    }

    /**
     * Getter method
     * @return number representing the cost to delete a gamePiece
     */
    public int getDelete() {
        return this.delete;
    }

    /**
     * Increments cost for Swap
     * @param num increment the cost to
     */
    public void incrementSwap(int num) {
        this.swap += num;
    }

    public void incrementExtraTurn(int num) {
        this.extraTurn += num;
    }

    public void incrementDelete(int num) {
        this.delete += num;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  MovesPoints) {
            MovesPoints object = (MovesPoints) obj;
            return this.delete == object.getDelete() &&
                    this.extraTurn == object.getExtraTurn() &&
                    this.place == object.getPlace() &&
                    this.swap == object.getSwap();
        }
        return false;
    }
}
