package com.example.comptictactoe.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

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
        binding.playGame.setOnClickListener(view -> nextPage());


    }


    private void nextPage() {
        try {
            mainViewModel.navigateToGame
                    (binding.playerName1.getText().toString(),
                            binding.playerName2.getText().toString());
            Intent i = new Intent(this, GameActivity3x3.class);
            i.putExtra("PlayerOne", binding.playerName1.getText().toString());
            i.putExtra("PlayerTwo", binding.playerName2.getText().toString());
            startActivity(i);
        }
        catch (Exception e) {
            Toast.makeText(this, "Both names needs to be inputted!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}