package com.yus.quran.core.data

import android.util.Log
import com.yus.quran.core.network.quran.QuranApiService
import com.yus.quran.core.network.quran.QuranEditionItem
import com.yus.quran.core.network.quran.SurahItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class QuranRemoteDataSource(private val quranApiService: QuranApiService) {

    suspend fun getListSurah() : Flow<NetworkResponse<List<SurahItem>>> =
        flow {
            try {
                val getListSurah = quranApiService.getListSurah()
                val listSurah = getListSurah.listSurah
                emit(NetworkResponse.Success(listSurah))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(QuranRemoteDataSource::class.java.simpleName, "getListSurah: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailSurahWithQuranEditions(number: Int) : Flow<NetworkResponse<List<QuranEditionItem>>> =
        flow {
            try {
                val getListAyah = quranApiService.getDetailSurahWithQuranEditions(number)
                val quranEdition = getListAyah.quranEdition
                emit(NetworkResponse.Success(quranEdition))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(QuranRemoteDataSource::class.java.simpleName, "getListSurah: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)
}