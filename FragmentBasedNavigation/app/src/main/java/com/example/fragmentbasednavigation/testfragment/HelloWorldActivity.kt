package com.example.fragmentbasednavigation.testfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentbasednavigation.R
import com.example.fragmentbasednavigation.databinding.ActivityHelloWorldBinding
import com.github.javafaker.Faker

class HelloWorldActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHelloWorldBinding

    private val faker = Faker.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelloWorldBinding.inflate(layoutInflater).also { setContentView(it.root) }

        setSupportActionBar(binding.toolbar)

        if(savedInstanceState == null) {
            val fragment = CounterFragment.newInstance(
                counterValue = 1,
                quote = createQuote()
            )
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit()
        }
    }

    fun createQuote(): String {
        return faker.harryPotter().quote()
    }

    fun getScreenCount(): Int {
        return supportFragmentManager.backStackEntryCount + 1
    }
}