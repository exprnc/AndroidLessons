package com.example.activitybasednavigation

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}