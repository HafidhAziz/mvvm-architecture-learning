package com.example.sehatqapplicationtest.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.sehatqapplicationtest.R
import com.example.sehatqapplicationtest.databinding.FragmentPurchaseHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by M Hafidh Abdul Aziz on 21/01/21.
 */

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    lateinit var binding: FragmentPurchaseHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_purchase_history, container, false)
        return binding.root
    }


}