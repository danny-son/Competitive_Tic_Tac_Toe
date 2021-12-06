package com.example.comptictactoe.View.Instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.comptictactoe.R


/**
 * A simple [Fragment] subclass.
 * Use the [instructionGainPoints.newInstance] factory method to
 * create an instance of this fragment.
 */
class InstructionGainPoints : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instruction_gain_points, container, false)
    }
}