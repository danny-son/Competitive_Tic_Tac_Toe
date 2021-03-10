package com.example.comptictactoe;



import java.io.Serializable;

//class for the player
public class Player implements Serializable {
    private String name;
    private GamePiece gp;
    private int points;
    private boolean currentTurn;
    private boolean turnMade;


    public Player(String name, GamePiece gp, int points, boolean currentTurn, boolean turnMade) {
        this.name = name;
        if (gp.equals(new EmptyGP())) {
            throw new IllegalArgumentException("Player's GamePiece must be either X or O");
        }
        else {
            this.gp = gp;
        }
        this.points  = points;
        this.currentTurn = currentTurn;
        this.turnMade = turnMade;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GamePiece getGP() {
        return this.gp;
    }

    public void setGP() {
        if (this.gp.equals(new X())) {
            this.gp = new O();
        } else if (this.gp == new O()) {
            this.gp = new X();
        }
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean getTurnMade() {
        return this.turnMade;
    }

    public void setTurnMade(boolean val) {
        this.turnMade = val;
    }

    public boolean getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(boolean currentTurn) {
        this.currentTurn = currentTurn;
    }
}
