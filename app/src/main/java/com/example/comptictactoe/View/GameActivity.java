package com.example.comptictactoe.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.comptictactoe.Model.Adapter.GridAdapter;
import com.example.comptictactoe.Model.Moves;
import com.example.comptictactoe.Model.MovesEnabled;
import com.example.comptictactoe.Model.Player;
import com.example.comptictactoe.Model.TicTacToe;
import com.example.comptictactoe.R;
import com.example.comptictactoe.ViewModel.GameplayViewModel;
import com.example.comptictactoe.databinding.ActivityGameBinding;



/**
 * Activity to Represent our 3x3 grid
 */
public class GameActivity extends AppCompatActivity {


    private GameplayViewModel gameViewModel;
    private ActivityGameBinding binding;

    //creates our game and player information retrieved from Main Page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        gameViewModel = new ViewModelProvider(this).get(GameplayViewModel.class);

        int[] imageList = new int[9];
        GridAdapter gridAdapter = new GridAdapter(GameActivity.this, imageList);
        binding.gameGrid.setAdapter(gridAdapter);
        binding.gameGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int row = i / gameViewModel.getGame().getValue().getGrid().size();
                int col = i % gameViewModel.getGame().getValue().getGrid().size();
                MovesEnabled movesEnabled = gameViewModel.getGame().getValue().getMovesEnabled();
                Player player = gameViewModel.getCurrentPlayer();
                if (movesEnabled.getPlace() && !player.getTurnMade()) {
                    try {
                        gameViewModel.placePiece(player, row, col);
                        if (player.getGP().isOGamePiece()) {
                            gridAdapter.updateImage(R.drawable.ic_o, i);
                        }
                        else if (player.getGP().isXGamePiece()) {
                            gridAdapter.updateImage(R.drawable.ic_x, i);
                        }
                        binding.movePieceInstructionsText.setText(getString(R.string.piece_is_placed,
                                gameViewModel.retrievePlayerPiece()));
                        binding.increaseGridButton.setClickable(true);
                        binding.endTurnButton.setClickable(true);
                        binding.extraTurnButton.setClickable(true);
                    }
                    catch (Exception e) {
                        Toast.makeText(GameActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                else if (movesEnabled.getDelete() && !player.getTurnMade()) {
                    try {
                        gameViewModel.deletePiece(player, row, col);
                        gridAdapter.removeImage(i);
                        binding.movePieceInstructionsText.setText(getString(R.string.piece_deleted));
                        binding.increaseGridButton.setClickable(true);
                        binding.endTurnButton.setClickable(true);
                        binding.extraTurnButton.setClickable(true);
                    } catch (Exception e) {
                        Toast.makeText(GameActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                else if (movesEnabled.getSwap() && !player.getTurnMade()) {
                    if (!gameViewModel.isSwapFirstPiecePicked()) {
                        gameViewModel.swapFirstPiecePicked(row,col);
                        binding.movePieceInstructionsText.setText(getString(R.string.swap_piece_two));
                    }
                    else {
                        try {
                            gameViewModel.swapPieces(player, row, col);
                            binding.movePieceInstructionsText.setText(getString(R.string.swap_complete));
                            binding.increaseGridButton.setClickable(true);
                            binding.endTurnButton.setClickable(true);
                            binding.extraTurnButton.setClickable(true);
                            int indexBefore = gameViewModel.retrieveSwapIndexBefore();
                            int imageIdBefore = (int) gridAdapter.getItem(indexBefore);
                            gridAdapter.updateImage((int) gridAdapter.getItem(i), indexBefore);
                            gridAdapter.updateImage(imageIdBefore, i);
                        } catch (Exception e) {
                            Toast.makeText(GameActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpGame();
        setUpText();
        setUpMoves();

    }

    private void setUpMoves() {
        binding.endTurnButton.setOnClickListener(v -> {
            gameViewModel.endTurn();
            binding.movePieceInstructionsText.setVisibility(View.INVISIBLE);
            enableMoves();
        });
        binding.placePieceButton.setOnClickListener(v -> {
            initializePlacePieceButton();
            disableMoves();
        });

        binding.deleteButton.setOnClickListener(v -> {
            try {
                initializeDeleteButton();
                disableMoves();
            } catch (Exception e) {
                Toast.makeText(GameActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.swapButton.setOnClickListener(v -> {
            try {
                initializeSwapButton();
                disableMoves();
            } catch (Exception e) {
                Toast.makeText(GameActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.extraTurnButton.setOnClickListener(v -> {
            try {
                initializeExtraTurnButton();
            } catch (Exception e) {
                Toast.makeText(GameActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void disableMoves() {
        binding.placePieceButton.setClickable(false);
        binding.swapButton.setClickable(false);
        binding.endTurnButton.setClickable(false);
        binding.extraTurnButton.setClickable(false);
        binding.deleteButton.setClickable(false);
        binding.increaseGridButton.setClickable(false);
    }

    private void enableMoves() {
        binding.placePieceButton.setClickable(true);
        binding.swapButton.setClickable(true);
        binding.endTurnButton.setClickable(true);
        binding.extraTurnButton.setClickable(true);
        binding.deleteButton.setClickable(true);
        binding.increaseGridButton.setClickable(true);
    }


    private void initializePlacePieceButton() {
        if (gameViewModel.canPerformMove(Moves.PLACE)) {
                binding.movePieceInstructionsText.setText(getString(R.string.place_piece_text,
                        gameViewModel.retrievePlayerPiece()));
                binding.movePieceInstructionsText.setVisibility(View.VISIBLE);
                gameViewModel.setMove(Moves.PLACE, true);
        }
    }

    private void initializeDeleteButton() {
        if (gameViewModel.canPerformMove(Moves.DELETE)) {
            binding.movePieceInstructionsText.setText(getString(R.string.delete_piece_text));
            binding.movePieceInstructionsText.setVisibility(View.VISIBLE);
            gameViewModel.setMove(Moves.DELETE, true);
        }
        else {
            throw new IllegalArgumentException("Cannot use Delete Move, Not enough points, " +
                    "or the other player has no pieces in the grid");
        }
    }

    private void initializeSwapButton() {
        if (gameViewModel.canPerformMove(Moves.SWAP)) {
            binding.movePieceInstructionsText.setText(getString(R.string.swap_piece_one));
            binding.movePieceInstructionsText.setVisibility(View.VISIBLE);
            gameViewModel.setMove(Moves.SWAP, true);
        }
        else {
            throw new IllegalArgumentException("Need at least One Piece on the grid in order to swap, " +
                    "or Not Enough Points!");
        }
    }

    private void initializeExtraTurnButton() {
        if (gameViewModel.canPerformMove(Moves.EXTRA_TURN)) {
            binding.movePieceInstructionsText.setText(getString(R.string.extra_turn_text));
            binding.movePieceInstructionsText.setVisibility(View.VISIBLE);
            gameViewModel.performExtraTurn();
            enableMoves();
            binding.extraTurnButton.setClickable(false);
        }
        else {
            throw new IllegalArgumentException("Either not enough points, " +
                    "original turn has not been made, or you already used the extra turn for this turn");
        }
    }

    /**
     * Sets up and adds observers to our textView
     */
    private void setUpText() {
        TicTacToe game = gameViewModel.getGame().getValue();
        binding.playerOnePointsText.setText(getString
                (R.string.player_one_header,
                        game.getPlayerOne().getName(),
                        game.getPlayerOne().getPoints()));
        binding.playerTwoPointsText.setText(getString
                (R.string.player_two_header,
                        game.getPlayerTwo().getName(),
                        game.getPlayerTwo().getPoints()));
        binding.turnNumberText.setText(getString(R.string.turn_number_text,
                game.getTurnNumber()));
    }

    private void setUpGame() {
        Intent i = getIntent();
        String playerName1 = i.getStringExtra("PlayerOne");
        String playerName2 = i.getStringExtra("PlayerTwo");
        gameViewModel.createGame(playerName1,playerName2);
        gameViewModel.getGame().observe(this, new Observer<TicTacToe>() {
            @Override
            public void onChanged(TicTacToe ticTacToe) {
                if (ticTacToe.getPlayerOne().getCurrentTurn()) {
                    updateTextViews(ticTacToe.getPlayerOne());
                }
                else {
                    updateTextViews(ticTacToe.getPlayerTwo());
                }
                int turnsLeft = gameViewModel.turnsLeftToIncreaseSize();
                binding.playerOnePointsText.setText(getString
                        (R.string.player_one_header,
                                ticTacToe.getPlayerOne().getName(),
                                ticTacToe.getPlayerOne().getPoints()));
                binding.playerTwoPointsText.setText(getString
                        (R.string.player_two_header,
                                ticTacToe.getPlayerTwo().getName(),
                                ticTacToe.getPlayerTwo().getPoints()));
                binding.increaseGridButton.setText(getString(R.string.increase_size, turnsLeft));
            }
        });
    }

    private void updateTextViews(Player player) {
        binding.deleteButton.setText(getString(R.string.delete, player.getMoves().getDelete()));
        binding.playerTurnNameText.setText(getString(R.string.player_turn_text, player.getName()));
        binding.swapButton.setText(getString(R.string.swap, player.getMoves().getSwap()));
        binding.extraTurnButton.setText(getString(R.string.extra_turn,player.getMoves().getExtraTurn()));
        binding.endTurnButton.setText(getString(R.string.end_turn,
                gameViewModel.pointsGained(player)));
        binding.turnNumberText.setText(getString(R.string.turn_number_text,
                gameViewModel.getGame().getValue().getTurnNumber()));
    }
}