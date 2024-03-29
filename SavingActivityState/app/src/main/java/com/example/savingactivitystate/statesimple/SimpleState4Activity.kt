package com.example.savingactivitystate.statesimple

import android.graphics.Color
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.savingactivitystate.R
import com.example.savingactivitystate.databinding.ActivityCounterBinding
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import kotlin.random.Random

class SimpleState4Activity : AppCompatActivity() {
    lateinit var binding: ActivityCounterBinding

    lateinit var state: State

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.incrementButton.setOnClickListener { increment() }
        binding.randomColorButton.setOnClickListener { setRandomColor() }
        binding.switchVisibilityButton.setOnClickListener { switchVisibility() }

        state = savedInstanceState?.getParcelable(KEY_STATE) ?: State(
            counterValue = 0,
            counterTextColor = ContextCompat.getColor(this, R.color.purple_700),
            counterIsVisible = true
        )
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, state)
    }

    private fun increment() {
        state.counterValue++
        renderState()
    }

    private fun setRandomColor() {
        state.counterTextColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun switchVisibility() {
        state.counterIsVisible = !state.counterIsVisible
        renderState()
    }

    private fun renderState() = with(binding) {
        counterTextView.setText(state.counterValue.toString())
        counterTextView.setTextColor(state.counterTextColor)
        counterTextView.visibility = if(state.counterIsVisible) View.VISIBLE else View.INVISIBLE
    }

    @Parcelize
    class State(
        var counterValue: Int,
        var counterTextColor: Int,
        var counterIsVisible: Boolean
    ) : Parcelable

    companion object {
        @JvmStatic private val KEY_STATE = "STATE"
    }
}