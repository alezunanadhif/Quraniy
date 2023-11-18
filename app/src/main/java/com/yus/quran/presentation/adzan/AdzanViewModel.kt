package com.yus.quran.presentation.adzan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yus.quran.core.data.AdzanRepository
import com.yus.quran.core.data.Resource
import com.yus.quran.core.domain.model.AdzanDataResult

class AdzanViewModel(private val adzanRepository: AdzanRepository) : ViewModel() {

    fun getDailyAdzanTime(): LiveData<Resource<AdzanDataResult>> = adzanRepository.getResultDailyAdzanTime()
}