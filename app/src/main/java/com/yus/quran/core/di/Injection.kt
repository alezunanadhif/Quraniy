package com.yus.quran.core.di

import android.content.Context
import com.yus.quran.core.data.AdzanRepository
import com.yus.quran.core.data.QuranRepository
import com.yus.quran.core.data.network.RemoteDataSource
import com.yus.quran.core.data.local.CalendarPreferences
import com.yus.quran.core.data.local.LocationPreferences
import com.yus.quran.core.data.network.ApiConfig

object Injection {
    private val quranApiService = ApiConfig.getQuranService
    private val adzanApiService = ApiConfig.getAdzanTimeService
    private val remoteDataSource = RemoteDataSource(quranApiService, adzanApiService)
    fun provideQuranRepository(): QuranRepository {
        return QuranRepository(remoteDataSource)
    }

    fun provideAdzanRepository(context: Context): AdzanRepository {
        val locationPreferences = LocationPreferences(context)
        val calendarPrefrences = CalendarPreferences()
        return AdzanRepository(remoteDataSource, locationPreferences, calendarPrefrences)
    }
}