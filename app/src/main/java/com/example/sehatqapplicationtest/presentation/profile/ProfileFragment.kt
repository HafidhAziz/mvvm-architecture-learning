package com.example.sehatqapplicationtest.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.databinding.FragmentProfileBinding
import com.example.sehatqapplicationtest.presentation.login.LoginActivity
import com.example.sehatqapplicationtest.presentation.purchasehistory.PurchaseHistoryActivity
import com.example.sehatqapplicationtest.util.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by M Hafidh Abdul Aziz on 21/01/21.
 */

@AndroidEntryPoint
class ProfileFragment : Fragment(), ProfileView {

    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        setupToolbar()
        setupUI()

        return binding.root
    }

    override fun setupToolbar() {
        binding.toolbarProfile.apply {
            btnBack.visibility = View.GONE
            searchLayout.visibility = View.GONE
            tvTitle.apply {
                text = getString(R.string.profile)
                visibility = View.VISIBLE
            }
        }
    }

    override fun setupUI() {
        binding.apply {
            purchaseHistoryMenu.setOnClickListener {
                PurchaseHistoryActivity.startThisActivity(requireContext())
            }
            logoutMenu.setOnClickListener {
                PreferenceManager.loginStatus = false
                activity?.finish()
                LoginActivity.startThisActivity(requireContext())
            }
            loggedInUser.text =
                getString(R.string.logged_in_as, PreferenceManager.rememberUsernameValue)
        }
    }
}