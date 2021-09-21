package com.example.comptictactoe.Model;



import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Class to represent the TicTacToe Model
 * Handles setting up, and running the game, and determines game status
 */
public class TicTacToe implements TicTacToeModel {
    private final Player p1;
    private final Player p2;
    private int rowSize;
    private int colSize;
    private ArrayList<ArrayList<GamePiece>> gameBoard;
    private int turnNumber;
    private GridSize gridSizeEnum;
    private MovesEnabled movesEnabled;

    /**
     * Main Constructor
     * @param p1 Player One
     * @param p2 Player Two
     * @param row int to represent the number of rows in our gameBoard
     * @param col int to represent the number of columns in our gameBoard
     */
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
        this.turnNumber = 0;
        this.gridSizeEnum = GridSize.THREE_BY_THREE;
        this.movesEnabled = new MovesEnabled();
    }

    public void setGridSizeEnum() {
        switch (gridSizeEnum) {
            case THREE_BY_THREE:
                this.gridSizeEnum = GridSize.FIVE_BY_FIVE;
                break;
            case FIVE_BY_FIVE:
                this.gridSizeEnum = GridSize.SEVEN_BY_SEVEN;
                break;
            default: break;
        }

    }

    public Enum<GridSize> getGridSizeEnum() {
        return this.gridSizeEnum;
    }

    public MovesEnabled getMovesEnabled() {
        return this.movesEnabled;
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
            p.setTurnMade(true);
            p.setPoints(p.getPoints() - p.getMoves().getPlace());
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
    public void swapPieces(Player p, int rowBefore, int colBefore, int rowAfter, int colAfter) {
        GamePiece tempPieceBefore = gameBoard.get(rowBefore).get(colBefore);
        gameBoard.get(rowBefore).set(colBefore, gameBoard.get(rowAfter).get(colAfter));
        gameBoard.get(rowAfter).set(colAfter, tempPieceBefore);
        p.setPoints(p.getPoints() - p.getMoves().getSwap());
        p.getMoves().incrementSwap(2);
    }

    @Override
    public void deletePiece(Player p, int row, int col) {
        gameBoard.get(row).set(col, new EmptyGP());
        p.setPoints(p.getPoints() - p.getMoves().getDelete());
        p.getMoves().incrementDelete(1);
    }

    @Override
    public void endTurn() {
        if (p1.getCurrentTurn()) {
            p1.setCurrentTurn(false);
            p1.setTurnMade(false);
            p2.setCurrentTurn(true);
            p2.setTurnMade(false);
        }
        else if (p2.getCurrentTurn()) {
            p2.setCurrentTurn(false);
            p2.setTurnMade(false);
            p1.setCurrentTurn(true);
            p1.setTurnMade(false);
        }
        turnNumber++;
    }


    public ArrayList<ArrayList<GamePiece>> getGrid() {
        return this.gameBoard;
    }

    @Override
    public void increaseGrid() {
        try{
            this.rowSize = rowSize + 2;
            this.colSize = colSize + 2;
            ArrayList<ArrayList<GamePiece>> newGrid = new ArrayList<ArrayList<GamePiece>>();
            for (int r = 0; r < rowSize; r++) {
                ArrayList<GamePiece> newRow = new ArrayList<GamePiece>();
                for (int c = 0; c < colSize; c++) {
                    if ((r == 0 || r == rowSize - 1) ||
                            ((r > 0 && r < rowSize - 1) && (c == 0 || c == colSize - 1))) {
                        newRow.add(new EmptyGP());
                    }
                    else if ((r > 0 && r < rowSize - 1) && (c > 0 && c < colSize - 1)) {
                        newRow.add(gameBoard.get(r - 1).get(c - 1));
                    }
                }
                newGrid.add(newRow);
            }
            this.setGrid(newGrid);
        }
        catch (Exception e) {
            throw(e);
        }
    }

    @Override
    public void setGrid(ArrayList<ArrayList<GamePiece>> grid) {
        this.gameBoard = grid;
    }

    public Player getPlayerOne() {
        return this.p1;
    }

    public Player getPlayerTwo() {
        return this.p2;
    }


    public int getTurnNumber() {
        return this.turnNumber;
    }

    @Override
    public void gainExtraTurn(Player p) {
        p.setTurnMade(false);
        p.setPoints(p.getPoints() - p.getMoves().getExtraTurn());
        p.getMoves().incrementExtraTurn(3);
    }

    /**
     * @return 2D array that represents a copy of our gameBoard
     */
    private ArrayList<ArrayList<GamePiece>> createBoardCopy() {
        ArrayList<ArrayList<GamePiece>> board = new ArrayList<>(gameBoard);
        for (int i = 0; i < gameBoard.size(); i++) {
            ArrayList<GamePiece> row = new ArrayList<>(gameBoard.get(i));
            for (int j = 0; j < gameBoard.get(i).size(); j++) {
                row.add(gameBoard.get(i).get(j));
            }
            board.add(row);
        }
        return board;
    }

    public int getTurnNumberToIncreaseGrid() {
        if (this.gridSizeEnum == GridSize.THREE_BY_THREE) {
            return 4;
        }
        else if (this.gridSizeEnum == GridSize.FIVE_BY_FIVE) {
            return 9;
        }
        else {
            return 0;
        }
    }

    @Override
    public int accumulatePoints(Player p) {
        int points = 0;
        ArrayList<ArrayList<GamePiece>> board = createBoardCopy();
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (j == board.get(i).size() - 1 && i == board.size() -1) {
                    continue;
                }
                else if (i == board.size() -1) {
                    if (p.getGP().equals(board.get(i).get(j)) &&
                            p.getGP().equals(board.get(i).get(j + 1))) {
                        board.get(i).set(j, new EmptyGP());
                        board.get(i).set(j + 1, new EmptyGP());
                        points++;
                    }
                }
                else if (j == board.get(i).size() - 1) {
                    if (p.getGP().equals(board.get(i).get(j)) &&
                            p.getGP().equals(board.get(i + 1).get(j))) {
                        board.get(i).set(j, new EmptyGP());
                        board.get(i+ 1).set(j, new EmptyGP());
                        points++;
                    }
                }
                else {
                    if (p.getGP().equals(board.get(i).get(j)) &&
                            p.getGP().equals(board.get(i).get(j + 1))) {
                        board.get(i).set(j, new EmptyGP());
                        board.get(i).set(j + 1, new EmptyGP());
                        points++;
                    }
                    else if (p.getGP().equals(board.get(i).get(j)) &&
                            p.getGP().equals(board.get(i + 1).get(j))) {
                        board.get(i).set(j, new EmptyGP());
                        board.get(i+ 1).set(j, new EmptyGP());
                        points++;
                    }
                }
            }
        }
        return points;
    }


    public Character getPlayerPiece() {
        if (p1.getCurrentTurn()) {
            return 'X';
        }
        else {
            return 'O';
        }
    }
}
