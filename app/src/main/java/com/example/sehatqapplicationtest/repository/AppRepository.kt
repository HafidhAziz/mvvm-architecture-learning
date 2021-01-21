package com.example.sehatqapplicationtest.repository

import com.example.sehatqapplicationtest.api.ApiHelper
import javax.inject.Inject

/**
 * Created by M Hafidh Abdul Aziz on 21/01/21.
 */

class AppRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getHomeData() = apiHelper.getHomeData()
}