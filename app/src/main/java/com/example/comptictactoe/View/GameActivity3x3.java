package com.example.comptictactoe.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comptictactoe.Model.EmptyGP;
import com.example.comptictactoe.Model.GamePiece;
import com.example.comptictactoe.Model.O;
import com.example.comptictactoe.Model.Player;
import com.example.comptictactoe.R;
import com.example.comptictactoe.Model.TicTacToe;
import com.example.comptictactoe.Model.X;
import com.example.comptictactoe.ViewModel.IViewModelGamePlay;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Activity to Represent our 3x3 grid
 */
public class GameActivity3x3 extends AppCompatActivity implements IViewModelGamePlay {

    TicTacToe game;
    HashMap<Button,Integer> buttonMap = new HashMap<Button, Integer>();
    boolean delete = false;
    boolean swap = false;
    boolean place = false;
    Button buttonSwapOne;
    Button buttonSwapTwo;
    String text = "";
    //creates a map of our moves and sets the price for each variable
    HashMap<Button,Integer> buttonMovesMap = new HashMap<Button, Integer>();




    //creates our game and player information retrieved from Main Page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_3x3);
        Intent i = getIntent();
        String playerName1 =  (String) i.getStringExtra("PlayerOne");
        String playerName2 = (String) i.getStringExtra("PlayerTwo");
        Player p1 = new Player(playerName1,new X(),3,true,false);
        Player p2 = new Player(playerName2,new O(),3,false,false);
        game = new TicTacToe(p1,p2,3,3);
        ArrayList<Button> buttonList = createButtons(game.getGrid().size());
        createButtonMap(buttonList);
        changeTurnView();
    }


    @Override
    public void selectMoves(View v) {
        if (place) {
            placePiece(v);
        }
        else if (delete) {
            deletePiece(v);
        }
        else if (swap) {
            swapPiece(v);
        }
    }

    @Override
    public void deletePiece(View v) {
        Player p1 = game.getPlayer(true);
        Player p2 = game.getPlayer(false);
        Button b = (Button)v;

        int index = buttonMap.get(b);
        int row = index / game.getGrid().size();
        int col = index % game.getGrid().size();
        if ((p1.getCurrentTurn() && !game.getGrid().get(row).get(col).isOGamePiece())
                || (p2.getCurrentTurn() && !game.getGrid().get(row).get(col).isXGamePiece())) {
            Toast.makeText(this,"You can Only delete your opponent's game piece",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        game.deletePiece(row,col);
        //update & edit our Buttons
        resetMovesButtons();
        findViewById(R.id.movePieceText).setVisibility(View.INVISIBLE);

        //reset our two buttons we selected
        if (p1.getCurrentTurn() && !p1.getTurnMade()) {
            p1.setTurnMade(true);
        }
        else if (p2.getCurrentTurn() && !p2.getTurnMade()) {
            p2.setTurnMade(true);
        }
        Button click = findViewById(R.id.endTurn);
        click.setClickable(true);
    }


    @Override
    public void swapPiece(View v) {
        if (buttonSwapOne == null) {
            buttonSwapOne = (Button)v;
            TextView t = findViewById(R.id.movePieceText);
            text = "Select Where You Want to Swap the Piece:";
            t.setText(text);
            buttonSwapOne.setClickable(false);
        }
        else if (buttonSwapTwo == null) {
            //performs the swap on pieces
            buttonSwapTwo = (Button) v;
            int indexBefore = buttonMap.get(buttonSwapOne);
            int indexAfter = buttonMap.get(buttonSwapTwo);
            int rowIndexBefore = indexBefore / game.getGrid().size();
            int colIndexBefore = indexBefore % game.getGrid().size();
            int rowIndexAfter = indexAfter / game.getGrid().size();
            int colIndexAfter = indexAfter % game.getGrid().size();
            game.swapPieces(rowIndexBefore, colIndexBefore, rowIndexAfter, colIndexAfter);


            Player p1 = game.getPlayer(true);
            Player p2 = game.getPlayer(false);

            TextView t = findViewById(R.id.movePieceText);
            t.setVisibility(View.INVISIBLE);

            //check if game is over
            if (p1.getCurrentTurn() && !p1.getTurnMade()) {
                p1.setTurnMade(true);
            }
            else if (p2.getCurrentTurn() && !p2.getTurnMade()) {
                p2.setTurnMade(true);
            }
            resetMovesButtons();
            Button click = findViewById(R.id.endTurn);
            click.setClickable(true);
            didPlayerWin(p1);
            didPlayerWin(p2);
        }
    }

    @Override
    public void deletePieceInit(View v) {
        Player p1 = game.getPlayer(true);
        Player p2 = game.getPlayer(false);
        TextView t = findViewById(R.id.movePieceText);
        boolean playerOneDeleteValid = false;
        boolean playerTwoDeleteValid = false;
        for (int r = 0; r < game.getGrid().size(); r++) {
            for (int c = 0; c < game.getGrid().get(r).size(); c++) {
                if (game.getGrid().get(r).get(c).equals(p1.getGP())) {
                    playerTwoDeleteValid = true;
                }
                else if (game.getGrid().get(r).get(c).equals(p2.getGP())) {
                    playerOneDeleteValid = true;
                }
            }
        }
        if ((p1.getCurrentTurn() && !playerOneDeleteValid) ||
                (p2.getCurrentTurn() &&!playerTwoDeleteValid)) {
            Toast.makeText(this, "Delete can only be performed " +
                    "if your opponent has " +
                    "a game piece on the grid!", Toast.LENGTH_SHORT).show();
            return;
        }
        text = "Select Piece To Delete:";
        t.setText(text);
        t.setVisibility(View.VISIBLE);
        enableOrDisableAllButtons(buttonMap,true);
        setButtonClickable(buttonMovesMap, false);
        delete = true;
    }

    //creates our map of buttons
    public void createButtonMap(ArrayList<Button> buttonList) {
        buttonMap = new HashMap<Button, Integer>();
        for (int i = 0; i < buttonList.size(); i++) {
            buttonMap.put(buttonList.get(i), i);
        }
        setButtonClickable(buttonMap, false);
        if (buttonMovesMap.size() == 0) {
            buttonMovesMap.put((Button) findViewById(R.id.swap), 0);
            buttonMovesMap.put((Button) findViewById(R.id.endTurn),1);
            buttonMovesMap.put((Button) findViewById(R.id.delete), 2);
            buttonMovesMap.put((Button) findViewById(R.id.place),3);
            enableOrDisableAllButtons(buttonMovesMap,true);
        }
    }

    @Override
    public void removeButtonsFromActivity() {
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.mainLayout);
        for (Button b: buttonMap.keySet()) {
            layout.removeView(b);
        }
    }

    //OnClick when player selects to place a piece
    public void placePieceInit(View v) {
        TextView movePieceText = findViewById(R.id.movePieceText);
        text = "Place a piece";
        movePieceText.setText(text);
        movePieceText.setVisibility(View.VISIBLE);
        setButtonClickable(buttonMovesMap,false);
        setButtonClickable(buttonMap,true);
        place = true;
    }

    //OnClick method when player selects an area to place their piece
    public void placePiece(View v) {
        Button b = (Button)v;
        int index = buttonMap.get(b);
        int rowIndex = index / game.getGrid().size();
        int colIndex = index % game.getGrid().size();
        Player p1 = game.getPlayer(true);
        Player p2 = game.getPlayer(false);
        try {
            if (p1.getCurrentTurn() && !p1.getTurnMade()) {
                placePieceHelper(p1, rowIndex, colIndex);

            }
            else if (p2.getCurrentTurn() && !p2.getTurnMade()) {
                placePieceHelper(p2, rowIndex, colIndex);

            }
            else if ((p1.getCurrentTurn() && p1.getTurnMade()) ||
                    p2.getCurrentTurn() && p2.getTurnMade()) {
                Toast.makeText(this,"Your Turn was Already Used! Either end " +
                                "your turn or Spend Credits to buy an Extra Turn.",
                        Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }


    //helper for the player to swap between two pieces
    public void placePieceHelper(Player player, int row, int col) {
        game.makeMove(player,row,col);
        findViewById(R.id.movePieceText).setVisibility(View.INVISIBLE);
        player.setTurnMade(true);
        resetMovesButtons();
        findViewById(R.id.endTurn).setClickable(true);
        didPlayerWin(player);

    }

    //helper to determine if the Player won the game
    public void didPlayerWin(Player player) {
        TextView gameStatusText = findViewById(R.id.gameStatus);
        if (game.isGameOver(player)) {
            text = player.getName() + " Wins!";
            gameStatusText.setText(text);
            gameStatusText.setVisibility(View.VISIBLE);
            setButtonClickable(buttonMap,false);
            setButtonClickable(buttonMovesMap, false);
            findViewById(R.id.extraTurn).setClickable(false);
        }
    }

    //just sets all buttons in the map to be clickable or not
    public void setButtonClickable(HashMap<Button,Integer> map, boolean clickable) {
        if (clickable) {
            for (Button b: map.keySet()) {
                b.setClickable(true);
            }
        }
        else  {
            for (Button b: map.keySet()) {
                b.setClickable(false);
            }
        }
    }

    //updates our buttons to change text depending on game piece on our display and duplicate
    public void updateButtonsText(HashMap<Button, Integer> map) {
        for (Button b: map.keySet()) {
            int index = map.get(b);
            int rowIndex = index / game.getGrid().size();
            int colIndex = index % game.getGrid().size();
            GamePiece piece = game.getGrid().get(rowIndex).get(colIndex);
            if (piece.isOGamePiece()) {
                b.setText("O");
            }
            else if (piece.isXGamePiece()) {
                b.setText("X");
            }
            else {
                b.setText("");
            }
        }
    }

    /*
    sets the clickables of buttons
    if false: sets all buttons that are non empty to clickable
    if true: sets all buttons that are empty to clickable
     */
    public void setGamePieceClickable(HashMap<Button, Integer> map, boolean emptyPieces) {
        if (emptyPieces) {
            for (Button b: map.keySet()) {
                int index = map.get(b);
                int rowIndex = index / game.getGrid().size();
                int colIndex = index % game.getGrid().size();
                if (game.getGrid().get(rowIndex).get(colIndex).isEmptyGamePiece()) {
                    b.setClickable(true);
                }
            }
        }
        else  {
            for (Button b: map.keySet()) {
                int index = map.get(b);
                int rowIndex = index / game.getGrid().size();
                int colIndex = index % game.getGrid().size();
                if (!game.getGrid().get(rowIndex).get(colIndex).isEmptyGamePiece()) {
                    b.setClickable(true);
                }
            }
        }
    }

    //sets all buttons in our map to be enabled or not(both visible and clickable)
    public void enableOrDisableAllButtons(HashMap<Button, Integer> map, boolean enable) {
        if (enable) {
            for (Button b: map.keySet()) {
                b.setClickable(true);
                b.setVisibility(View.VISIBLE);
            }
        }
        else {
            for (Button b: map.keySet()) {
                b.setClickable(false);
                b.setVisibility(View.GONE);
            }
        }
    }

    //performs operations to show text
    public void swapPieceInit(View v) {
        Player p1 = game.getPlayer(true);
        Player p2 = game.getPlayer(false);
        TextView t = findViewById(R.id.movePieceText);
        if ((p1.getCurrentTurn() && p1.getTurnMade()) || p2.getCurrentTurn() && p2.getTurnMade()) {
            Toast.makeText(this,"Your Turn was Already Used! Either end " +
                            "your turn or Spend Credits to buy an Extra Turn.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        else  {
            text = "Select Piece To Move:";
            t.setText(text);
            t.setVisibility(View.VISIBLE);
            setButtonClickable(buttonMovesMap, false);
            swap = true;
        }
    }


    //updates and resets the visibility and clicks of our buttons back to our non-dup buttons
    public void resetMovesButtons() {
        delete = false;
        updateButtonsText(buttonMap);
        setButtonClickable(buttonMap,false);
    }





    //Text View to reveal who's turn it is
    public void changeTurnView() {
        Player p1 = game.getPlayer(true);
        Player p2 = game.getPlayer(false);
        TextView t = findViewById(R.id.turnName);
        if (p1.getCurrentTurn()) {
            text = "It's " + p1.getName() + "'s Turn!";
            t.setText(text);
            //show player score and interface
        }
        else if (p2.getCurrentTurn()) {
            text = "It's " + p2.getName() + "'s Turn!";
            t.setText(text);
            //show player score and interface
        }
    }

    //ends the turn in the game
    public void endTurnView(View v) {
        Player p1 = game.getPlayer(true);
        Player p2 = game.getPlayer(false);
        game.endTurn(p1,p2);
        changeTurnView();
        setButtonClickable(buttonMovesMap, true);
        findViewById(R.id.extraTurn).setClickable(true);
        findViewById(R.id.gameStatus).setVisibility(View.INVISIBLE);
        resetMoveStatus();
    }

    /**
     * resets the current status of the moves that were clicked on and the buttons
     * that associated with it
     */
    private void resetMoveStatus() {
        swap = false;
        place = false;
        delete = false;
        buttonSwapOne = null;
        buttonSwapTwo = null;
    }

    //allows the player to gain an extra turn
    public void addTurnView(View v) {
        String errMsg = "Player needs to perform an action before trying to gain an extra turn!";
        Player p1 = game.getPlayer(true);
        Player p2 = game.getPlayer(false);
        if (p1.getCurrentTurn()){
            if (p1.getTurnMade()) {
                p1.setTurnMade(false);
                setButtonClickable(buttonMovesMap, true);
                findViewById(R.id.extraTurn).setClickable(false);
                TextView t = findViewById(R.id.gameStatus);
                t.setText("Player has gained an extra turn!");
                t.setVisibility(View.VISIBLE);
                resetMoveStatus();
            }
            else {
                Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
            }
        }
        else if (p2.getCurrentTurn()) {
            if (p2.getTurnMade()) {
                p2.setTurnMade(false);
                setButtonClickable(buttonMovesMap, true);
                findViewById(R.id.extraTurn).setClickable(false);
                TextView t = findViewById(R.id.gameStatus);
                t.setText("Player has gained an extra turn!");
                t.setVisibility(View.VISIBLE);
                resetMoveStatus();
            }
            else {
                Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void dynamicallyIncreaseGrid(View v) {
        if (game.getTurnNumber() > 2 && game.getGrid().size() < 7) {
            game.increaseGrid();
            ArrayList<Button> newGrid = createButtons(game.getGrid().size());
            removeButtonsFromActivity();
            createButtonMap(newGrid);
        }
        else if (game.getGrid().size() == 7) {
            Toast.makeText(this, "Cannot increase grid size any further",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Need to have 2 turns completed to perform this",
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public ArrayList<Button> createButtons(int gridSize) {
        int xGap;
        int yGap;
        int xPos;
        int yPos;
        int btWidth;
        int btHeight;
        switch (gridSize) {
            case 3:
                xGap = 320;
                yGap = 190;
                xPos = 95;
                yPos = 525;
                btWidth = 250;
                btHeight = 105;
                break;

            case 5:
                xGap = 300;
                yGap = 190;
                xPos = 95;
                yPos = 400;
                btWidth = 160;
                btHeight = 100;
                break;

            case 7:
                xGap = 45;
                yGap = 45;
                xPos = 95;
                yPos = 375;
                btWidth = 130;
                btHeight = 100;
                break;
            default:
                xGap = 0;
                yGap = 0;
                xPos = 100;
                yPos = 100;
                btWidth = 0;
                btHeight = 0;
                break;
        }
        ArrayList<Button> list = new ArrayList<Button>();
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.mainLayout);
        for (int i = 0; i < gridSize * gridSize; i++) {
            ConstraintSet set = new ConstraintSet();

            Button bt = new Button(this);
            // set view id, else getId() returns -1
            bt.setId(View.generateViewId());
            bt.setLayoutParams(new ConstraintLayout.LayoutParams(btWidth,btHeight));
            bt.setBackgroundColor(Color.rgb(255,140,140));
            bt.setX(((i%gridSize) * xGap) + xPos);
            bt.setY(((i/gridSize) * yGap) + yPos);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectMoves(v);
                }
            });
            layout.addView(bt, 0);
            set.clone(layout);
            set.connect(bt.getId(), ConstraintSet.TOP, layout.getId(),
                    ConstraintSet.TOP, 0);
            set.connect(bt.getId(), ConstraintSet.LEFT, layout.getId(),
                    ConstraintSet.LEFT, 0);
            set.applyTo(layout);
            list.add(bt);
        }
        return list;
    }
}
