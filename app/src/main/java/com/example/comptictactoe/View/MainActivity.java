package com.example.comptictactoe.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comptictactoe.R;

/**
 * The Main Activity, the page that first displays when users first open the app
 */
public class MainActivity extends AppCompatActivity {

    String p1 = "";
    String p2 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Allows players to input their name in the textBox
    public void inputText(View v) {
        Button b = (Button)v;
        if (v.equals(findViewById(R.id.enter1))) {
            EditText t = findViewById(R.id.playerName1);
            String input = t.getText().toString();
            if (input.length() > 10) {
                Toast.makeText(this,"Name too long, must be at most 10 characters",
                        Toast.LENGTH_LONG).show();
            }
            else {
                p1 = input;
                b.setVisibility(View.GONE);
                t.setFocusable(false);
            }
        }
        else if (v.equals(findViewById(R.id.enter2))) {
            EditText t = findViewById(R.id.playerName2);
            String input = t.getText().toString();
            if (input.length() >= 10) {
                Toast.makeText(this,"Name too long, must be at most 10 characters",
                        Toast.LENGTH_LONG).show();
            }
            else {
                p2 = input;
                b.setVisibility(View.GONE);
                t.setFocusable(false);
            }
        }
    }

    //OnClick to go to the next page
    public void nextPage(View v) {

        if (p1.equals("") || p2.equals("")) {
            Toast.makeText(this, "Both players need to enter their Name!",
                    Toast.LENGTH_LONG).show();
        }
        else {
            Intent i = new Intent(this, GameActivity3x3.class);
            i.putExtra("PlayerOne", p1);
            i.putExtra("PlayerTwo", p2);
            startActivity(i);
        }
    }
}