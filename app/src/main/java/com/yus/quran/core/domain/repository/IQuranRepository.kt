package com.yus.quran.core.domain.repository

import com.yus.quran.core.data.Resource
import com.yus.quran.core.domain.model.QuranEdition
import com.yus.quran.core.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface IQuranRepository {

    fun getListSurah() : Flow<Resource<List<Surah>>>

    fun getDetailSurahWithQuranEditions(number: Int) : Flow<Resource<List<QuranEdition>>>
}