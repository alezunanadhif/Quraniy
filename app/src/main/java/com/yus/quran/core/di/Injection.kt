package com.yus.quran.core.di

import com.yus.quran.core.data.QuranRemoteDataSource
import com.yus.quran.core.data.QuranRepository
import com.yus.quran.core.network.ApiConfig

object Injection {
    fun provideQuranRepository(): QuranRepository {
        val apiService = ApiConfig.getQuranService
        val remoteDataSource = QuranRemoteDataSource(apiService)
        return QuranRepository(remoteDataSource)
    }
}