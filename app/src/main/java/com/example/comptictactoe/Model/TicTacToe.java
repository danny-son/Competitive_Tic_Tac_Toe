package com.example.comptictactoe.Model;



import java.util.ArrayList;

public class TicTacToe implements TicTacToeModel {
    private final Player p1;
    private final Player p2;
    private int rowSize;
    private int colSize;
    private ArrayList<ArrayList<GamePiece>> gameBoard;

    //different Size constructor;
    public TicTacToe(Player p1, Player p2, int row, int col) {
        this.p1 = p1;
        this.p2 = p2;
        if (row < 3 || col < 3) {
            throw new IllegalArgumentException("row and col should be size at least 3");
        }
        else {
            this.rowSize = row;
            this.colSize = col;
        }
        this.gameBoard = createGrid(this.rowSize, this.colSize);
    }



    @Override
    public ArrayList<ArrayList<GamePiece>> createGrid(int row, int column) {
        EmptyGP mt = new EmptyGP();
        ArrayList<ArrayList<GamePiece>> wholeGrid = new ArrayList<ArrayList<GamePiece>>();
        for (int r = 0; r < row; r++) {
            ArrayList<GamePiece> rowGP = new ArrayList<GamePiece>();
            for (int c = 0; c < column; c++) {
                rowGP.add(mt);
            }
            wholeGrid.add(rowGP);
        }
        return wholeGrid;

    }

    @Override
    public boolean isGameOver(Player p) {
        //first check for all the rows if the player contains all of its Pieces in a single row
        for (int r = 0; r < rowSize; r++) {
            if (checkRow(gameBoard, p.getGP(), r)) {
                return true;
            }
        }
        //check for all the columns if the players contains all of its Pieces in a single column
        for (int c = 0; c < colSize; c++) {
            if (checkColumn(gameBoard, p.getGP(),c)) {
                return true;
            }
        }

        //check for both diagonals if the players contains all of it Pieces in diagonal form
        for (int i = 0; i < 2; i++) {
           if (i == 0) {
               if (checkDiagonal(gameBoard, p.getGP(), 0)) {
                   return true;
               }
           }
           else  {
               if (checkDiagonal(gameBoard, p.getGP(), rowSize -1)) {
                   return true;
               }
           }
        }
        return false;
    }

    /**
     * Helper Method to Determine if the Player has won, by having the same GamePiece in the given
     * row
     * @param gameBoard 2D list of GamePieces we are checking
     * @param gp GamePiece we want to check for consistency
     * @param row index number we are checking
     * @return boolean to see if all the GamePieces are in the same row.
     */
    private boolean checkRow(ArrayList<ArrayList<GamePiece>> gameBoard, GamePiece gp, int row) {
        ArrayList<GamePiece> rowIndex = gameBoard.get(row);
        boolean isConnected = true;
        for (int c = 0; c < colSize; c++) {
            if (!rowIndex.get(c).equals(gp)) {
                isConnected = false;
                break;
            }
        }
        return isConnected;
    }

    /**
     * Helper Method to Determine if the Player has won, by having the same GamePiece in the given
     * column
     * @param gameBoard 2D list of GamePieces we are checking
     * @param gp GamePiece we want to check for consistency
     * @param col index number we are checking
     * @return boolean to see if all the GamePieces are in the same column.
     */
    private boolean checkColumn(ArrayList<ArrayList<GamePiece>> gameBoard, GamePiece gp, int col) {
        boolean isConnected = true;
        for (int r = 0; r < rowSize; r++) {
            GamePiece colIndex = gameBoard.get(r).get(col);
            if (!colIndex.equals(gp)) {
                isConnected = false;
                break;
            }
        }
        return isConnected;
    }

    /**
     * Helper Method to Determine if the Player has won, by having the same GamePiece in the given
     * diagonal direction
     * @param gameBoard 2D list of GamePieces we are checking
     * @param gp GamePiece we want to check for consistency
     * @param row index number we are checking (0 = topLeft - bottomRight, 1 = bottomLeft to topRight)
     * @return boolean to see if all the GamePieces are in the same diagonal direction.
     */
    private boolean checkDiagonal(ArrayList<ArrayList<GamePiece>> gameBoard, GamePiece gp, int row) {
        boolean isConnected = true;

        if (row == 0) {
            for (int r = row; r < rowSize; r++) {
                if (!gp.equals(gameBoard.get(r).get(r))){
                    isConnected = false;
                    break;
                }
            }
        }
        else if (row == rowSize -1) {
            for (int r = row; r >= 0; r--) {
                if (!gp.equals(gameBoard.get(r).get(row - r))) {
                    isConnected = false;
                    break;
                }
            }
        }
        return isConnected;
    }

    @Override
    public void makeMove(Player p, int row, int column) {
        if (moveValid(row,column)) {
            gameBoard.get(row).set(column, p.getGP());
        }
        else {
            throw new IllegalArgumentException("Move needs to be on the board or not " +
                    "have a piece on where u placed the piece");
        }
    }

    /**
     * Helper Function to determine if the Move is valid for the player
     * @param row row index they intend to use
     * @param column column index they intend  to use
     * @return boolean to determine if the move is valid.
     */
    private boolean moveValid(int row, int column) {
        return row >= 0 && column >= 0 && row < rowSize && column < colSize &&
                gameBoard.get(row).get(column).isEmptyGamePiece();
    }

    @Override
    public void increaseGrid() {
        this.rowSize = rowSize + 2;
        this.colSize = colSize + 2;
    }


    @Override
    public void swapPieces(int rowBefore, int colBefore, int rowAfter, int colAfter) {
        GamePiece tempPieceBefore = gameBoard.get(rowBefore).get(colBefore);
        gameBoard.get(rowBefore).set(colBefore, gameBoard.get(rowAfter).get(colAfter));
        gameBoard.get(rowAfter).set(colAfter, tempPieceBefore);
    }

    @Override
    public void deletePiece(int row, int col) {
        gameBoard.get(row).set(col, new EmptyGP());
    }

    @Override
    public void endTurn(Player p1, Player p2) {
        if (p1.getCurrentTurn()) {
            p1.setCurrentTurn(false);
            p1.setTurnMade(false);
            p2.setCurrentTurn(true);
            p2.setTurnMade(true);
        }
        else if (p2.getCurrentTurn()) {
            p2.setCurrentTurn(false);
            p2.setTurnMade(false);
            p1.setCurrentTurn(true);
            p1.setTurnMade(true);
        }
    }

    @Override
    public ArrayList<ArrayList<GamePiece>> getGrid() {
        return this.gameBoard;
    }

    @Override
    public void setGrid(ArrayList<ArrayList<GamePiece>> newGrid) {
        this.gameBoard = newGrid;
    }

    @Override
    public Player getPlayer(boolean playerOne) {
        if (playerOne) {
            return this.p1;
        }
        else {
            return this.p2;
        }
    }
}
