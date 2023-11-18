package com.yus.quran.core.di

import android.content.Context
import com.yus.quran.core.data.AdzanRepository
import com.yus.quran.core.data.QuranRepository
import com.yus.quran.core.data.RemoteDataSource
import com.yus.quran.core.data.local.LocationPreferences
import com.yus.quran.core.data.network.ApiConfig

object Injection {
    fun provideQuranRepository(): QuranRepository {
        val quranApiService = ApiConfig.getQuranService
        val adzanApiService = ApiConfig.getAdzanTimeService
        val remoteDataSource = RemoteDataSource(quranApiService, adzanApiService)
        return QuranRepository(remoteDataSource)
    }

    fun provideAdzanRepository(context: Context): AdzanRepository {
        val quranApiService = ApiConfig.getQuranService
        val adzanApiService = ApiConfig.getAdzanTimeService
        val remoteDataSource = RemoteDataSource(quranApiService, adzanApiService)
        val locationPreferences = LocationPreferences(context)
        return AdzanRepository(remoteDataSource, locationPreferences)
    }
}