package com.nadhif.quran.core.domain.repository

import androidx.lifecycle.LiveData
import com.yus.quran.core.data.Resource
import com.yus.quran.core.domain.model.City
import com.yus.quran.core.domain.model.DailyAdzan
import kotlinx.coroutines.flow.Flow

interface IAdzanRepository {

    fun getLastKnownLocation(): LiveData<List<String>>
    fun searchCity(city: String): Flow<Resource<List<City>>>
    fun getDailyAdzanTime(
        id: String,
        year: String,
        month: String,
        date: String
    ): Flow<Resource<DailyAdzan>>
}