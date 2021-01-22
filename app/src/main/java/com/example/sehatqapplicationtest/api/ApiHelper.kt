package com.example.sehatqapplicationtest.api

import com.example.sehatqapplicationtest.data.HomeContentEntity
import retrofit2.Response

interface ApiHelper {

    suspend fun getHomeData(): Response<List<HomeContentEntity>>
}