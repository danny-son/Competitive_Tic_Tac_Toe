package com.example.comptictactoe.ViewModel;

import android.view.View;
import android.widget.Button;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.comptictactoe.Model.Player;
import com.example.comptictactoe.Model.TicTacToe;

import java.util.ArrayList;
import java.util.HashMap;

public class GameplayViewModel extends ViewModel implements IViewModelGamePlay {

    private TicTacToe game;

    private MutableLiveData<HashMap<Button,Integer>> buttonMap = new MutableLiveData<HashMap<Button, Integer>>();
    private String text = "";
    private int turnRequirement = 4;
    //creates a map of our moves and sets the price for each variable
    private HashMap<Button,Integer> buttonMovesMap = new HashMap<Button, Integer>();

    GameplayViewModel() {

    }


    public void createGame(Player playerOne, Player playerTwo) {
        game = new TicTacToe(playerOne, playerTwo, 3,3);
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
