package com.example.sehatqapplicationtest.presentation.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sehatqapplicationtest.api.ApiHelper
import com.example.sehatqapplicationtest.data.HomeContentEntity
import com.example.sehatqapplicationtest.repository.AppRepository
import com.example.sehatqapplicationtest.util.Resource
import kotlinx.coroutines.launch

/**
 * Created by M Hafidh Abdul Aziz on 21/01/21.
 */

class HomeViewModel @ViewModelInject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    private val _homeData = MutableLiveData<Resource<List<HomeContentEntity>>>()
    val homeData: LiveData<Resource<List<HomeContentEntity>>>
        get() = _homeData

    init {
        fetchHomeData()
    }

    private fun fetchHomeData() {
        viewModelScope.launch {
            _homeData.postValue(Resource.loading(null))
            appRepository.getHomeData().let {
                if (it.isSuccessful) {
                    _homeData.postValue(Resource.success(it.body()))
                } else _homeData.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}