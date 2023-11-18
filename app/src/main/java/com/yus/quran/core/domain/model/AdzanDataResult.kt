package com.yus.quran.core.domain.model

import com.yus.quran.core.data.Resource

data class AdzanDataResult(
    val listLocation: List<String>,
    val listCity: Resource<List<City>>
)
