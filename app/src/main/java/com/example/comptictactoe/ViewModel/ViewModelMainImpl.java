package com.example.comptictactoe.ViewModel;

import android.content.Intent;
import android.widget.Toast;

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
    @Override
    public void navigateToGame(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            throw new IllegalArgumentException();
        }
        return;
    }
}
