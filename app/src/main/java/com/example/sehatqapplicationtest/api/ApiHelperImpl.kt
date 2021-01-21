package com.example.sehatqapplicationtest.api

import com.example.sehatqapplicationtest.data.entity.HomeContentEntity
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: AppApiService) : ApiHelper {

    override suspend fun getHomeData(): Response<HomeContentEntity> = apiService.getHomeData()
}