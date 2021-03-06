package com.example.sehatqapplicationtest.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.databinding.ActivityLoginBinding
import com.example.sehatqapplicationtest.presentation.main.MainActivity
import com.example.sehatqapplicationtest.util.PreferenceManager
import com.example.sehatqapplicationtest.util.ViewUtils
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), LoginView {

    lateinit var binding: ActivityLoginBinding
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val callbackManager: CallbackManager = CallbackManager.Factory.create()
    private lateinit var auth: FirebaseAuth

    companion object {
        private const val RC_SIGN_IN =
            1003 // this generate a random int between 1 and 1000, WORKING OK

        fun startThisActivity(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        if (PreferenceManager.loginStatus) {
            MainActivity.startThisActivity(this)
            finish()
            return
        }

        auth = FirebaseAuth.getInstance()

        setupUI()
        setupFbSignIn()
        setupGoogleSignIn()
    }

    override fun setupUI() {
        if (PreferenceManager.rememberStatus) {
            binding.username.setText(PreferenceManager.rememberUsernameValue.orEmpty())
            binding.checkBox.isChecked = true
        }

        binding.btnSignIn.setOnClickListener {
            var isValid = true
            binding.apply {
                tilUsername.isErrorEnabled = false
                tilPassword.isErrorEnabled = false
                usernameError.visibility = View.GONE
                passwordError.visibility = View.GONE
            }
            if (binding.username.text.isNullOrEmpty()) {
                isValid = false
                binding.apply {
                    tilUsername.isErrorEnabled = true
                    tilUsername.error = "error"
                    usernameError.visibility = View.VISIBLE
                }
            }
            if (binding.password.text.isNullOrEmpty()) {
                isValid = false
                binding.apply {
                    tilPassword.isErrorEnabled = true
                    tilPassword.error = "error"
                    passwordError.visibility = View.VISIBLE
                }
            }

            if (isValid) {
                PreferenceManager.rememberStatus = binding.checkBox.isChecked
                PreferenceManager.rememberUsernameValue = binding.username.text.toString()
                ViewUtils.hideKeyboard(this, binding.password)
                goToMainPage()
            }
        }

        binding.tvFb.setOnClickListener {
            onClickFacebook()
        }

        binding.tvGoogle.setOnClickListener {
            onClickGoogle()
        }
    }

    override fun setupFbSignIn() {
        binding.loginFbButton.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val accessToken = AccessToken.getCurrentAccessToken()
                val isLoggedIn = accessToken != null && !accessToken.isExpired
                goToMainPage()
            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })
    }

    override fun setupGoogleSignIn() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.server_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    override fun onClickFacebook() {
        binding.loginFbButton.performClick()
    }

    override fun onClickGoogle() {
        val signInIntent = mGoogleSignInClient?.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onStart() {
        super.onStart()
        if (!PreferenceManager.loginStatus) {
            mGoogleSignInClient?.signOut()
        }
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(this)
        account?.let {
            goToMainPage()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...)
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun goToMainPage() {
        finish()
        PreferenceManager.loginStatus = true
        MainActivity.startThisActivity(this)
    }

    override fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
            PreferenceManager.rememberUsernameValue = account.displayName
            goToMainPage()
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(this, "google sign in failed", Toast.LENGTH_LONG).show()
        }
    }
}