package com.example.fragmentbasednavigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentbasednavigation.R
import com.example.fragmentbasednavigation.databinding.FragmentBoxBinding
import com.example.fragmentbasednavigation.fragments.navigator.HasCustomTitle
import com.example.fragmentbasednavigation.fragments.navigator.navigator

class BoxFragment: Fragment(), HasCustomTitle {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentBoxBinding.inflate(inflater, container, false).apply {
        toMainMenuButton.setOnClickListener { onToMainMenuPressed() }
    }.root

    private fun onToMainMenuPressed() {
        navigator().goToMenu()
    }

    override fun getTitleRes(): Int = R.string.box
}