package com.example.customviews2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customviews2.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var isFirstPlayer = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.ticTacToeField.ticTacToeField = TicTacToeField(10, 10)

        binding.ticTacToeField.actionListener = { row, column, field ->
            val cell = field.getCell(row, column)
            if (cell == Cell.EMPTY) {
                if (isFirstPlayer) {
                    field.setSell(row, column, Cell.PLAYER_1)
                } else {
                    field.setSell(row, column, Cell.PLAYER_2)
                }
                isFirstPlayer = !isFirstPlayer
            }
        }

        binding.randomFieldButton.setOnClickListener {

        }
    }
}