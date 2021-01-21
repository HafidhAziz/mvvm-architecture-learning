package com.example.sehatqapplicationtest.presentation.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.databinding.ActivityLoginBinding
import com.example.sehatqapplicationtest.presentation.main.MainActivity
import com.example.sehatqapplicationtest.util.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.btnSignIn.setOnClickListener {
            var isValid = true
            binding.apply {
                usernameError.visibility = View.GONE
                passwordError.visibility = View.GONE
            }
            if (binding.username.text.isNullOrEmpty()) {
                isValid = false
                binding.usernameError.visibility = View.VISIBLE
            }
            if (binding.password.text.isNullOrEmpty()) {
                isValid = false
                binding.passwordError.visibility = View.VISIBLE
            }

            if (isValid) {
                if (binding.checkBox.isChecked) {
                    PreferenceManager.rememberStatus = true
                }
                PreferenceManager.loginStatus = true
                MainActivity.startThisActivity(this)
            }
        }
    }
}