package com.nadhif.quran.presentation.quran

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yus.quran.core.data.QuranRepository

class QuranViewModel(private val quranRepository: QuranRepository) : ViewModel() {

    fun getListSurah() = quranRepository.getListSurah().asLiveData()

    fun getDetailSurahWithQuranEdition(number: Int) = quranRepository.getDetailSurahWithQuranEditions(number).asLiveData()
}