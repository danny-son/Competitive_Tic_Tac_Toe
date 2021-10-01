package com.example.comptictactoe.Model;

public class PlayerFactory {
    public PlayerFactory() {

    }

    public Player createPlayerOne(String name) {
        Player playerOne = new Player(name, new X(),2,true, false);
        return playerOne;
    }

    public Player createPlayerTwo(String name) {
        Player playerTwo = new Player(name, new O(), 2, false, false);
        return playerTwo;
    }
}
