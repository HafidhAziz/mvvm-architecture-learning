package com.example.sehatqapplicationtest.service

import com.google.gson.JsonObject
import retrofit2.http.GET

/**
 * Created by M Hafidh Abdul Aziz on 20/01/21.
 */

interface AppApiService {

    @GET("/home")
    suspend fun getHomeData(): JsonObject
}