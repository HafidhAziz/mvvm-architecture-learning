package com.example.sehatqapplicationtest.presentation.main

import androidx.fragment.app.Fragment

/**
 * Created by M Hafidh Abdul Aziz on 21/01/21.
 */

interface MainView {
    fun loadFragment(fragment: Fragment?): Boolean
}