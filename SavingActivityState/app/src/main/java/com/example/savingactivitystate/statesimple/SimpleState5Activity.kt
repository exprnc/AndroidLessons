package com.example.savingactivitystate.statesimple

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.savingactivitystate.R
import com.example.savingactivitystate.databinding.ActivityCounterBinding
import com.example.savingactivitystate.statesimple.SimpleState5ViewModel.State

class SimpleState5Activity : AppCompatActivity() {

    lateinit var binding: ActivityCounterBinding

    private val viewModel by viewModels<SimpleState5ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.incrementButton.setOnClickListener { viewModel.increment() }
        binding.randomColorButton.setOnClickListener { viewModel.setRandomColor() }
        binding.switchVisibilityButton.setOnClickListener { viewModel.switchVisibility() }

        viewModel.initState(
            savedInstanceState?.getParcelable(KEY_STATE) ?: State(
                counterValue = 0,
                counterTextColor = ContextCompat.getColor(this, R.color.purple_700),
                counterIsVisible = true
            )
        )

        viewModel.state.observe(this, Observer {
            renderState(it)
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, viewModel.state.value)
    }

    private fun renderState(state: State) = with(binding) {
        counterTextView.setText(state.counterValue.toString())
        counterTextView.setTextColor(state.counterTextColor)
        counterTextView.visibility = if (state.counterIsVisible) VISIBLE else INVISIBLE
    }

    companion object {
        @JvmStatic
        val KEY_STATE = "STATE"
    }
}