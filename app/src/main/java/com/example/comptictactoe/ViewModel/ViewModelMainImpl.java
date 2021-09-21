package com.example.comptictactoe.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelMainImpl extends ViewModel implements IViewModelMain {

    private MutableLiveData<String> playerOne = new MutableLiveData<String>();
    private MutableLiveData<String> playerTwo = new MutableLiveData<String>();


    public ViewModelMainImpl(){
        playerOne.setValue("");
        playerTwo.setValue("");
    }

    public LiveData<String> getPlayerOne() {
        return this.playerOne;
    }

    public LiveData<String> getPlayerTwo() {
        return this.playerTwo;
    }

    public void updatePlayerOne(String s) {
        playerOne.setValue(s);
    }

    public void updatePlayerTwo(String s) {
        playerTwo.setValue(s);
    }

    @Override
    public boolean playerNamesFilled() {
        if (playerOne.getValue().length() == 0 || playerTwo.getValue().length() == 0) {
            return false;
        }
        return true;
    }
}
