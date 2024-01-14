package com.example.listviewandspinner

import android.os.Bundle
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.listviewandspinner.databinding.ActivitySimpleListViewBinding

class SimpleAdapterActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySimpleListViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimpleListViewBinding.inflate(layoutInflater).also { setContentView(it.root) }
//        setupListViewSimple()
        setupListViewWithSimpleGenerateData()
    }

    private fun setupListViewSimple() {
        val data = listOf(
            mapOf(
                KEY_TITLE to "First title",
                KEY_DESCRIPTION to "The first some description"
            ),
            mapOf(
                KEY_TITLE to "Second title",
                KEY_DESCRIPTION to "The second some description"
            ),
            mapOf(
                KEY_TITLE to "Third title 333",
                KEY_DESCRIPTION to "The third some description"
            ),
        )

        val adapter = SimpleAdapter(
            this,
            data,
            android.R.layout.simple_list_item_2,
            arrayOf(KEY_TITLE, KEY_DESCRIPTION),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        binding.listView.adapter = adapter

        binding.listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItemTitle = data[position][KEY_TITLE]
            val selectedItemDescription = data[position][KEY_DESCRIPTION]

            val dialog = AlertDialog.Builder(this)
                .setTitle(selectedItemTitle)
                .setMessage(getString(R.string.item_selected_message, selectedItemDescription))
                .setPositiveButton("OK") { dialog, which -> }
                .create()
            dialog.show()
        }
    }

    private fun setupListViewWithSimpleGenerateData() {
        val data = (1..100).map {
            mapOf(
                KEY_TITLE to "Item #$it",
                KEY_DESCRIPTION to "Description #$it",
            )
        }

        val adapter = SimpleAdapter(
            this,
            data,
            R.layout.item_custom,
            arrayOf(KEY_TITLE, KEY_DESCRIPTION),
            intArrayOf(R.id.title_text_view, R.id.description_text_view)
        )

        binding.listView.adapter = adapter


    }

    companion object {
        @JvmStatic val KEY_TITLE = "title"
        @JvmStatic val KEY_DESCRIPTION = "description"
    }
}