package com.example.sehatqapplicationtest.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by M Hafidh Abdul Aziz on 22/01/21.
 */

object ViewUtils {

    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
    }
}