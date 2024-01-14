package com.example.randomimage

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.randomimage.databinding.ActivityMainBinding
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var useKeyword: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getRandomImageButton.setOnClickListener {
            onGetRandomImagePressed()
        }

        binding.keywordEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                return@setOnEditorActionListener onGetRandomImagePressed()
            }
            return@setOnEditorActionListener false
        }

        binding.keywordEditText.addTextChangedListener {
            onGetRandomImagePressed()
        }

        binding.useKeywordSwitch.setOnClickListener {
            this.useKeyword = binding.useKeywordSwitch.isChecked
            updateUi()
        }

        binding.keywordRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            onGetRandomImagePressed()
        }

        updateUi()
    }

    private fun onGetRandomImagePressed(): Boolean {
        val checkedId = binding.keywordRadioGroup.checkedRadioButtonId
        val keywordRadioButton = binding.keywordRadioGroup.findViewById<RadioButton>(checkedId).text.toString()
        val keywordEditText = binding.keywordEditText.text.toString()
        if (useKeyword && keywordEditText.isBlank()) {
            binding.keywordEditText.error = "Keyword is empty"
            return true
        }

        val encodedKeyword = if (useKeyword) {
            URLEncoder.encode(keywordEditText, StandardCharsets.UTF_8.name())
        } else if(!keywordRadioButton.contains("Random") && !useKeyword) {
            URLEncoder.encode(keywordRadioButton, StandardCharsets.UTF_8.name())
        } else {
            ""
        }

        binding.progressBar.visibility = View.VISIBLE
        Glide.with(this)
            .load("https://source.unsplash.com/random/800x600?$encodedKeyword")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.visibility = View.GONE
                    return false
                }
            })
            .into(binding.randomImageView)

        return false
    }

    private fun updateUi() = with(binding) {
        useKeywordSwitch.isChecked = useKeyword
        if(useKeyword) {
            keywordRadioGroup.visibility = View.GONE
            keywordEditText.visibility = View.VISIBLE
        } else {
            keywordRadioGroup.visibility = View.VISIBLE
            keywordEditText.visibility = View.GONE
        }
    }
}