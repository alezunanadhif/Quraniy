package com.yus.quran.utils

import com.yus.quran.core.domain.model.Ayah
import com.yus.quran.core.domain.model.QuranEdition
import com.yus.quran.core.domain.model.Surah
import com.yus.quran.core.network.quran.AyahsItem
import com.yus.quran.core.network.quran.QuranEditionItem
import com.yus.quran.core.network.quran.SurahItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {

    @JvmName("mapSurahResponseToDomainFunction")
    fun mapResponseToDomain(input: List<SurahItem>): Flow<List<Surah>> {
        val listSurah = ArrayList<Surah>()
        input.map {
            val surah = Surah(
                number = it.number,
                name = it.name,
                englishName = it.englishName,
                englishNameTranslation = it.englishNameTranslation,
                numberOfAyahs = it.numberOfAyahs,
                revelationType = it.revelationType
            )
            listSurah.add(surah)
        }
        return flowOf(listSurah)
    }

    @JvmName("mapQuranEditionResponseToDomainFunction")
    fun mapResponseToDomain(input: List<QuranEditionItem>): Flow<List<QuranEdition>> {
        val listQuranEdition = ArrayList<QuranEdition>()
        input.map {
            val quranEdition = QuranEdition(
                number = it.number,
                name = it.name,
                englishName = it.englishName,
                englishNameTranslation = it.englishNameTranslation,
                revelationType = it.revelationType,
                numberOfAyahs = it.numberOfAyahs,
                listAyahs = mapListAyahResponseToDomain(it.listAyahs)
            )
            listQuranEdition.add(quranEdition)
        }
        return flowOf(listQuranEdition)
    }

    private fun mapListAyahResponseToDomain(input: List<AyahsItem>): List<Ayah> {
        val listAyah = ArrayList<Ayah>()
        input.map {
            val ayah = Ayah(
                number = it.number,
                text = it.text,
                numberInSurah = it.numberInSurah,
                audio = it.audio
            )
            listAyah.add(ayah)
        }
        return listAyah
    }
}