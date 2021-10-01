package com.example.comptictactoe.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;
import com.example.comptictactoe.ViewModel.ViewModelMainImpl;
import com.example.comptictactoe.databinding.ActivityMainBinding;

/**
 * The Main Activity, the page that first displays when users first open the app
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ViewModelMainImpl mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        mainViewModel = new ViewModelProvider(this).get(ViewModelMainImpl.class);
        setContentView(binding.getRoot());
        binding.playGameButton.setOnClickListener(view -> nextPage());
        binding.playerOneNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mainViewModel.updatePlayerOne(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.playerTwoNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mainViewModel.updatePlayerTwo(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void nextPage() {
        try {
            if (mainViewModel.playerNamesFilled()) {
                Log.i("Player", mainViewModel.getPlayerOne().getValue());
                Log.i("Player", mainViewModel.getPlayerTwo().getValue());
                Intent i = new Intent(this, GameActivity.class);
                i.putExtra("PlayerOne", binding.playerOneNameEditText.getText().toString());
                i.putExtra("PlayerTwo", binding.playerTwoNameEditText.getText().toString());
                startActivity(i);
            }
        }
        catch (Exception e) {
            Toast.makeText(this, "Both names needs to be inputted!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}