package com.example.dialogfragment

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

fun DialogFragment.showToast(@StringRes messageRes: Int) {
    com.example.dialogfragment.showToast(requireContext(), messageRes)
}

fun AppCompatActivity.showToast(@StringRes messageRes: Int) {
    com.example.dialogfragment.showToast(this, messageRes)
}

fun showToast(context: Context, @StringRes messageRes: Int) {
    Toast.makeText(context, messageRes, Toast.LENGTH_SHORT).show()
}