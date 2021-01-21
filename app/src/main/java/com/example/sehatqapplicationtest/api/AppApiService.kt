package com.example.sehatqapplicationtest.api

import com.example.sehatqapplicationtest.data.entity.HomeContentEntity
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */

interface AppApiService {

    @GET("/home")
    suspend fun getHomeData(): Response<HomeContentEntity>
}