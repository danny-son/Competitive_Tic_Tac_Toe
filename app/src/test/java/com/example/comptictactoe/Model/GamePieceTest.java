package com.example.comptictactoe.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class GamePieceTest {

    private O oPiece;
    private X xPiece;
    private EmptyGP mtPiece;

    private TicTacToe gameBoard;

    @Before
    public void setUp() throws Exception {
        oPiece = new O();
        xPiece = new X();
        mtPiece = new EmptyGP();
    }

    @Test
    public void testEmptyGamePiece() {
        try {
            setUp();
        } catch (Exception e) {
            System.out.println(e);
        }
        assertTrue(oPiece.isOGamePiece());
        assertTrue(xPiece.isXGamePiece());
        assertTrue(mtPiece.isEmptyGamePiece());

        assertFalse(oPiece.isEmptyGamePiece());
        assertFalse(oPiece.isXGamePiece());
        assertFalse(xPiece.isEmptyGamePiece());
        assertFalse(xPiece.isOGamePiece());
        assertFalse(mtPiece.isOGamePiece());
        assertFalse(mtPiece.isXGamePiece());

    }

}