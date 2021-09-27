package com.example.comptictactoe.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.comptictactoe.Model.Moves;
import com.example.comptictactoe.Model.Player;
import com.example.comptictactoe.Model.PlayerFactory;
import com.example.comptictactoe.Model.TicTacToe;

import java.util.*;

public class GameplayViewModel extends ViewModel  {

    private final MutableLiveData<TicTacToe> game = new MutableLiveData<>();
    private final PlayerFactory playerFactory = new PlayerFactory();


    public GameplayViewModel() { }

    public LiveData<TicTacToe> getGame() {
        return this.game;
    }

    public void createGame(String playerOneName, String playerTwoName) {

        Player playerOne = playerFactory.createPlayerOne(playerOneName);
        Player playerTwo = playerFactory.createPlayerTwo(playerTwoName);
        game.setValue(new TicTacToe(playerOne, playerTwo, 3, 3));
    }

    public int turnsLeftToIncreaseSize() {
        if (this.getGame().getValue().getTurnNumberToIncreaseGrid() <=
                this.game.getValue().getTurnNumber()) {
            return 0;
        }
        return this.game.getValue().getTurnNumberToIncreaseGrid() -
                this.game.getValue().getTurnNumber();
    }

    public void endTurn() {
        getGame().getValue().endTurn();
        this.game.setValue(getGame().getValue());
        resetMovesButtons();
    }

    public void resetMovesButtons() {
        this.game.getValue().getMovesEnabled().resetMove();
    }

    public int pointsGained(Player p) {
        int pointsGain = this.game.getValue().accumulatePoints(p);
        p.setPointsGained(pointsGain);
        return p.getPointsGained();
    }

    public Character retrievePlayerPiece() {
        return this.game.getValue().getPlayerPiece();
    }

    public void setMove(Moves move, boolean value) {
        switch (move) {
            case PLACE:
                getGame().getValue().getMovesEnabled().setPlace(value);
                break;
            case SWAP:
                getGame().getValue().getMovesEnabled().setSwap(value);
                break;
            case DELETE:
                getGame().getValue().getMovesEnabled().setDelete(value);
                break;
            case EXTRA_TURN:
                getGame().getValue().getMovesEnabled().setExtraTurn(value);
                break;
            default: break;
        }
    }

    public boolean canPerformMove(Moves move) {
        Player player = getCurrentPlayer();
        switch (move) {
            case PLACE:
                return !player.getTurnMade();
            case SWAP:
                return !player.getTurnMade() &&
                        player.getPoints() >= player.getMoves().getSwap() &&
                        canSwap();
            case DELETE:
                return !player.getTurnMade() &&
                        player.getPoints() >= player.getMoves().getDelete() &&
                        canDeletePiece();
            case EXTRA_TURN:
                return player.getTurnMade() &&
                        player.getPoints() >= player.getMoves().getExtraTurn() &&
                        !game.getValue().getMovesEnabled().getExtraTurn();
            default: return false;
        }
    }

    public void performExtraTurn() {
        Player player = getCurrentPlayer();
        game.getValue().gainExtraTurn(player);
        game.setValue(game.getValue());
    }

    public Player getCurrentPlayer() {
        if (game.getValue().getPlayerOne().getCurrentTurn()) {
            return game.getValue().getPlayerOne();
        }
        else {
            return game.getValue().getPlayerTwo();
        }
    }


    public void placePiece(Player player, int row, int column) {
        getGame().getValue().makeMove(player, row, column);
        this.game.setValue(getGame().getValue());
    }

    public void deletePiece(Player player, int row, int column) {
        getGame().getValue().deletePiece(player, row, column);
        this.game.setValue(getGame().getValue());
    }

    public boolean isSwapFirstPiecePicked() {
        return game.getValue().getFirstSwapPicked();
    }

    public void swapFirstPiecePicked(int row, int col) {
        game.getValue().setFirstSwapPicked(true);
        game.getValue().setFirstSwapPieceRow(row);
        game.getValue().setFirstSwapPieceColumn(col);
    }

    public void swapPieces(Player player, int row, int col) {
        game.getValue().swapPieces(player, row, col);
        this.game.setValue(getGame().getValue());
    }

    public boolean canDeletePiece() {
        return game.getValue().canDeletePlayerPiece();
    }

    public boolean canSwap() {
        return (game.getValue().getPlayerOne().getNumPiecesInGrid() +
                game.getValue().getPlayerTwo().getNumPiecesInGrid() > 0);
    }

    public int retrieveSwapIndexBefore() {
        return game.getValue().getFirstSwapPieceRow() * game.getValue().getGrid().size() +
                game.getValue().getFirstSwapPieceColumn();
    }

}
