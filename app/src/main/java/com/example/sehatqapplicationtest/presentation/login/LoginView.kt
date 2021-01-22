package com.example.sehatqapplicationtest.presentation.login

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */

interface LoginView {
    fun setupFbSignIn()
    fun setupGoogleSignIn()
    fun onClickFacebook()
    fun onClickGoogle()
    fun goToMainPage()
    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>)
}