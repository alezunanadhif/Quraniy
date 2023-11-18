package com.yus.quran.core.domain.repository

import androidx.lifecycle.LiveData
import com.yus.quran.core.data.Resource
import com.yus.quran.core.domain.model.City
import kotlinx.coroutines.flow.Flow

interface IAdzanRepository {

    fun getLastKnownLocation(): LiveData<List<String>>
    fun searchCity(city: String): Flow<Resource<List<City>>>
}