package com.example.sehatqapplicationtest.api

import com.example.sehatqapplicationtest.data.entity.HomeContentEntity
import retrofit2.Response

interface ApiHelper {

    suspend fun getHomeData(): Response<HomeContentEntity>
}