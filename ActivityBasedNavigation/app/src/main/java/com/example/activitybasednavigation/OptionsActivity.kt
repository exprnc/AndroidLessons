package com.example.activitybasednavigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.activitybasednavigation.databinding.ActivityOptionsBinding
import com.example.activitybasednavigation.model.Options

class OptionsActivity : BaseActivity() {

    private lateinit var binding: ActivityOptionsBinding

    private lateinit var options: Options

    private lateinit var boxCountItems: List<BoxCountItem>
    private lateinit var adapter: ArrayAdapter<BoxCountItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater).also { setContentView(it.root) }

        options =
            savedInstanceState?.getParcelable<Options>(KEY_OPTIONS) ?: intent.getParcelableExtra(
                EXTRA_OPTIONS
            )
                    ?: throw IllegalArgumentException("You need to specify EXTRA_OPTIONS argument to launch this activity")

        setupSpinner()
        setupCheckBox()
        updateUi()

        binding.confirmButton.setOnClickListener { onConfirmPressed() }
        binding.cancelButton.setOnClickListener { onCancelPressed() }
    }

    private fun onConfirmPressed() {
        val intent = Intent()
        intent.putExtra(EXTRA_OPTIONS, options)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun onCancelPressed() {
        finish()
    }

    private fun setupSpinner() {
        boxCountItems =
            (1..6).map { BoxCountItem(it, resources.getQuantityString(R.plurals.boxes, it, it)) }
        adapter = ArrayAdapter(
            this,
            R.layout.item_spinner,
            boxCountItems
        )
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)

        binding.boxCountSpinner.adapter = adapter
        binding.boxCountSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val count = boxCountItems[position].count
                    options = options.copy(boxCount = count)
                }
            }
    }

    private fun setupCheckBox() {
        binding.enableTimerCheckBox.setOnClickListener {
            options = options.copy(isTimerEnabled = binding.enableTimerCheckBox.isChecked)
        }
    }

    private fun updateUi() {
        binding.enableTimerCheckBox.isChecked = options.isTimerEnabled

        val currentIndex = boxCountItems.indexOfFirst { it.count == options.boxCount }
        binding.boxCountSpinner.setSelection(currentIndex)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_OPTIONS, options)
    }

    companion object {
        @JvmStatic
        val EXTRA_OPTIONS = "EXTRA_OPTIONS"

        @JvmStatic
        private val KEY_OPTIONS = "KEY_OPTIONS"
    }

    class BoxCountItem(
        val count: Int,
        private val optionTitle: String
    ) {
        override fun toString(): String {
            return optionTitle
        }
    }
}