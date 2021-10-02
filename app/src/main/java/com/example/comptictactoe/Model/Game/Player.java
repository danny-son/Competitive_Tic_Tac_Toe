package com.example.comptictactoe.Model.Game;



import java.io.Serializable;

/**
 * Class to Resemble Player, holds information regarding their game status, currency and GamePiece
 */
public class Player implements Serializable {
    private String name;
    private GamePiece gp;
    private int numPiecesInGrid;
    private int points;
    private int pointsGained;
    private boolean currentTurn;
    private boolean turnMade;
    private MovesPoints movePoints;


    /**
     * Constructor for a player
     * @param name String represents the player's name
     * @param gp GamePiece piece that they will be picking
     * @param points Int, number of points they have
     * @param currentTurn boolean, determine if it is their turn or not
     * @param turnMade booelan, determine if they made their turn or not
     */
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
        this.movePoints = new MovesPoints();
        this.pointsGained = 0;
        this.numPiecesInGrid = 0;
    }


    /**
     * Gets the Player's Name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the players name
     * @param name String to change the name to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the Player's GamePiece
     * @return Gamepiece
     */
    public GamePiece getGP() {
        return this.gp;
    }

    /**
     * Changes the Player's Current GamePiece to its Opposite.
     */
    public void setGP() {
        if (this.gp.equals(new X())) {
            this.gp = new O();
        } else if (this.gp == new O()) {
            this.gp = new X();
        }
    }

    /**
     * Gets the Player's Points
     * @return Int
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the player's points
     * @param points Integer we want to set the Player's points to
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Gets the player's TurnMade
     * @return boolean
     */
    public boolean getTurnMade() {
        return this.turnMade;
    }

    /**
     * Sets the player's TurnMade
     * @param val Boolean value
     */
    public void setTurnMade(boolean val) {
        this.turnMade = val;
    }

    /**
     * Gets the Player's Current Turn
     * @return boolean value
     */
    public boolean getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Sets the Player's Current Turn
     * @param currentTurn boolean value
     */
    public void setCurrentTurn(boolean currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     * Getter Method
     * @return Moves for the player
     */
    public MovesPoints getMoves() {
        return this.movePoints;
    }


    public void setPointsGained(int pointsGained) {
        this.pointsGained = pointsGained;
    }

    public int getPointsGained() {
        return this.pointsGained;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            Player object = (Player) obj;
            return this.currentTurn == object.getCurrentTurn() &&
                    this.turnMade == object.getTurnMade() &&
                    this.gp.equals(object.getGP()) &&
                    this.name.equals(object.getName()) &&
                    this.points == object.getPoints() &&
                    this.movePoints.equals(object.getMoves());
        }
        else {
            return false;
        }
    }

    public int getNumPiecesInGrid() {
        return this.numPiecesInGrid;
    }

    public void setNumPiecesInGrid(int num) {
        this.numPiecesInGrid = num;
    }
}
