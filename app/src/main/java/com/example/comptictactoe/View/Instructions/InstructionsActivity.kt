package com.example.comptictactoe.View.Instructions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.comptictactoe.InstructionIncreaseGrid
import com.example.comptictactoe.R
import com.example.comptictactoe.View.MainActivity
import com.example.comptictactoe.databinding.ActivityInstructionsBinding

class InstructionsActivity : AppCompatActivity() {

    private var _binding: ActivityInstructionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInstructionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // sets our container to be the first page (how to play) by default
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.instruction_container, InstructionHowToPlay())
            .commit()
        // sets up our bottom navigation
        setUpBottomNavigation()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivityForResult(intent, 0)
        return super.onOptionsItemSelected(item)
    }

    private fun setUpBottomNavigation() {
        binding.instructionsBottomNav.setOnNavigationItemSelectedListener {
            val fragment: Fragment? = when (it.itemId) {
                R.id.how_to_play_item -> InstructionHowToPlay()
                R.id.moves_item -> InstructionsMoves()
                R.id.how_to_gain_points_item -> InstructionGainPoints()
                R.id.how_to_increase_grid_item -> InstructionIncreaseGrid()
                else -> null
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.instruction_container, fragment!!)
                .commit()
            true
        }
    }
}