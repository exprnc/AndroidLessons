package com.example.fragmentbasednavigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BuildCompat
import androidx.fragment.app.Fragment
import com.example.fragmentbasednavigation.BuildConfig
import com.example.fragmentbasednavigation.R
import com.example.fragmentbasednavigation.databinding.FragmentAboutBinding
import com.example.fragmentbasednavigation.fragments.navigator.HasCustomTitle
import com.example.fragmentbasednavigation.fragments.navigator.navigator

class AboutFragment: Fragment(), HasCustomTitle {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentAboutBinding.inflate(layoutInflater, container, false).apply {
        versionNameTextView.text = BuildConfig.VERSION_NAME
        versionCodeTextView.text = BuildConfig.VERSION_CODE.toString()
        okButton.setOnClickListener { onOkPressed() }
    }.root

    override fun getTitleRes(): Int = R.string.about

    private fun onOkPressed() {
        navigator().goBack()
    }
}