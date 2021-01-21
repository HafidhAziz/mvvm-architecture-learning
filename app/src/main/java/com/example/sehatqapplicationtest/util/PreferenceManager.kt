package com.example.sehatqapplicationtest.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */

object PreferenceManager {

    private const val NAME = "example.sehatqapplicationtest"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private val isLogin = Pair("IS_LOGIN", false)
    private val isRemember = Pair("IS_REMEMBER", false)

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit()
     * and apply() ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var loginStatus: Boolean
        get() = preferences.getBoolean(isLogin.first, isLogin.second)
        set(value) = preferences.edit {
            it.putBoolean(isLogin.first, value)
        }

    var rememberStatus: Boolean
        get() = preferences.getBoolean(isRemember.first, isRemember.second)
        set(value) = preferences.edit {
            it.putBoolean(isRemember.first, value)
        }

}