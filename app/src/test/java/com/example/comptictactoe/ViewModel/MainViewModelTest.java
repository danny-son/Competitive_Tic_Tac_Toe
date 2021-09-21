package com.example.comptictactoe.ViewModel;

import static org.junit.Assert.*;



import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class MainViewModelTest extends TestCase {

    private ViewModelMainImpl mainViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Before
    public void setup() {
        mainViewModel = new ViewModelMainImpl();
    }




    @Test
    public void testInit() {
        assertEquals(mainViewModel.getPlayerOne().getValue(), "");
        assertEquals(mainViewModel.getPlayerTwo().getValue(), "");
    }

    @Test
    public void testPlayerUpdate() {

        mainViewModel.updatePlayerOne("danny");
        assertEquals(mainViewModel.getPlayerOne().getValue(), "danny");
        assertEquals(mainViewModel.getPlayerTwo().getValue(), "");
        mainViewModel.updatePlayerTwo("son");
        mainViewModel.updatePlayerOne("ynnad");
        assertEquals(mainViewModel.getPlayerOne().getValue(), "ynnad");
        assertEquals(mainViewModel.getPlayerTwo().getValue(), "son");
    }

    @Test
    public void testPlayerNamesFilled() {
        assertFalse(mainViewModel.playerNamesFilled());
        mainViewModel.updatePlayerOne("s");
        assertFalse(mainViewModel.playerNamesFilled());
        mainViewModel.updatePlayerTwo("d");
        assertTrue(mainViewModel.playerNamesFilled());
    }
}