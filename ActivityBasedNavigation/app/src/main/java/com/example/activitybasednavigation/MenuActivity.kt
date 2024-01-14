package com.example.activitybasednavigation

import android.os.Bundle
import com.example.activitybasednavigation.databinding.ActivityMenuBinding

class MenuActivity : BaseActivity() {

    private lateinit var binding: ActivityMenuBinding

    private lateinit var options: Options

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater).also { setContentView(it.root) }


    }
}