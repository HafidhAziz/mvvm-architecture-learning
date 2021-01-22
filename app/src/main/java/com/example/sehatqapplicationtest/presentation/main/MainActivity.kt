package com.example.sehatqapplicationtest.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.databinding.ActivityMainBinding
import com.example.sehatqapplicationtest.presentation.home.HomeFragment
import com.example.sehatqapplicationtest.presentation.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainView,
    BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var binding: ActivityMainBinding

    companion object {

        fun startThisActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.bnMain.setOnNavigationItemSelectedListener(this)

        loadFragment(HomeFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.home_menu -> fragment = HomeFragment()
            R.id.profile_menu -> fragment = ProfileFragment()
        }
        return loadFragment(fragment)
    }

    override fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, fragment)
                .commit()
            return true
        }
        return false
    }

    override fun onResume() {
        super.onResume()
        this.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}