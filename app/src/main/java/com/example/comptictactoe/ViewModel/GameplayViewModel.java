package com.example.comptictactoe.ViewModel;

import android.view.View;
import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.comptictactoe.Model.Moves;
import com.example.comptictactoe.Model.Player;
import com.example.comptictactoe.Model.PlayerFactory;
import com.example.comptictactoe.Model.TicTacToe;

import java.util.ArrayList;
import java.util.HashMap;

public class GameplayViewModel extends ViewModel implements IViewModelGamePlay {

    private MutableLiveData<TicTacToe> game = new MutableLiveData<>();
    private final PlayerFactory playerFactory = new PlayerFactory();
    //private MutableLiveData<HashMap<Button,Integer>> buttonMap = new MutableLiveData<HashMap<Button, Integer>>();
    //creates a map of our moves and sets the price for each variable
    //private HashMap<Button,Integer> buttonMovesMap = new HashMap<Button, Integer>();

    public GameplayViewModel() {

    }

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
    }

    public int pointsGained(Player p) {
        return this.game.getValue().accumulatePoints(p);
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



    @Override
    public void createButtonMap(ArrayList<Button> buttonList) {

    }

    @Override
    public void removeButtonsFromActivity() {

    }

    @Override
    public void placePieceInit(View v) {

    }

    @Override
    public void placePiece(View v) {

    }

    @Override
    public void placePieceHelper(Player player, int row, int col) {

    }

    @Override
    public void didPlayerWin(Player player) {

    }

    @Override
    public void updateButtonsText(HashMap<Button, Integer> map) {

    }

    @Override
    public void setButtonClickable(HashMap<Button, Integer> map, boolean clickable) {

    }

    @Override
    public void setGamePieceClickable(HashMap<Button, Integer> map, boolean emptyPieces) {

    }

    @Override
    public void enableOrDisableAllButtons(HashMap<Button, Integer> map, boolean enable) {

    }

    @Override
    public void changeTurnView() {

    }

    @Override
    public void endTurnView(View v) {

    }

    @Override
    public void resetMovesButtons() {

    }

    @Override
    public void swapPieceInit(View v) {

    }

    @Override
    public void addTurnView(View v) {

    }

    @Override
    public void dynamicallyIncreaseGrid(View v) {

    }

    @Override
    public ArrayList<Button> createButtons(int gridSize) {
        return null;
    }

    @Override
    public void selectMoves(View v) {

    }

    @Override
    public void deletePiece(View v) {

    }

    @Override
    public void swapPiece(View v) {

    }

    @Override
    public void deletePieceInit(View v) {

    }

    @Override
    public void updateButtonMovesCost(Player p) {

    }
}
