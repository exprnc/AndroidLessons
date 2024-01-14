package com.example.listviewandspinner.baseadapter

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.listviewandspinner.R
import com.example.listviewandspinner.databinding.ActivityListViewBinding
import com.example.listviewandspinner.databinding.DialogAddCharacterBinding
import kotlin.random.Random

class BaseAdapterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListViewBinding

    private val data = mutableListOf(
        Character(id = 1, name = "Reptile", isCustom = false),
        Character(id = 2, name = "Subzero", isCustom = false),
        Character(id = 3, name = "Scorpion", isCustom = false),
        Character(id = 4, name = "Raiden", isCustom = false),
        Character(id = 5, name = "Smoke", isCustom = false),
    )

    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater).also { setContentView(it.root) }
        setupList()
        binding.addButton.setOnClickListener { onAddPressed() }
    }

    private fun setupList() {
        adapter = CharacterAdapter(data) {
            deleteCharacter(it)
        }
        binding.listView.adapter = adapter

        //Для listView
//        binding.listView.setOnItemClickListener { parent, view, position, id ->
//            showCharacterInfo(adapter.getItem(position))
//        }

        //Для spinner
        binding.listView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val character = data[position]
                binding.characterInfoTextView.text = getString(R.string.character_info, character.id, character.name)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.characterInfoTextView.text = ""
            }
        }
    }

    private fun onAddPressed() {
        val dialogBinding = DialogAddCharacterBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Create character")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") { d, which ->
                val name = dialogBinding.characterNameEditText.text.toString()
                if (name.isNotBlank()) {
                    createCharacter(name)
                }
            }.create()
        dialog.show()
    }

    private fun createCharacter(name: String) {
        val character = Character(
            id = Random.nextLong(),
            name = name,
            isCustom = true
        )
        data.add(character)
        adapter.notifyDataSetChanged()
    }

    private fun showCharacterInfo(character: Character) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(character.name)
            .setMessage(getString(R.string.character_info, character.id, character.name))
            .setPositiveButton("OK") { _, _ -> }
            .create()
        dialog.show()
    }

    private fun deleteCharacter(character: Character) {
        val listener = DialogInterface.OnClickListener { dialog, which ->
            if(which == DialogInterface.BUTTON_POSITIVE) {
                data.remove(character)
                adapter.notifyDataSetChanged()
            }
        }
        val dialog = AlertDialog.Builder(this)
            .setTitle("Delete Character")
            .setMessage(getString(R.string.character_delete_message, character.name))
            .setPositiveButton("Delete", listener)
            .setNegativeButton("Cancel", listener)
            .create()
        dialog.show()
    }
}