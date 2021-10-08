package com.example.comptictactoe.Model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

import com.example.comptictactoe.Model.Game.Player;
import com.example.comptictactoe.Model.Game.PlayerFactory;

@RunWith(JUnit4.class)
public class PlayerFactoryTest {

    private Player playerOne;
    private Player playerTwo;
    private final PlayerFactory factory = new PlayerFactory();

    @Before
    public void setUp() throws Exception {
        playerOne = factory.createPlayerOne("danny");
        playerTwo = factory.createPlayerTwo("josh");
    }

    @Test
    public void testPlayer() {
        assertEquals(playerOne,factory.createPlayerOne("danny"));
        assertEquals(playerTwo,factory.createPlayerTwo("josh"));
        assertNotEquals(playerOne,factory.createPlayerTwo("danny"));
        assertNotEquals(playerTwo, factory.createPlayerOne("josh"));
    }

}