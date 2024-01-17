package com.example.fragmentbasednavigation.fragments.navigator

import androidx.annotation.StringRes

interface HasCustomTitle {
    @StringRes
    fun getTitleRes(): Int
}