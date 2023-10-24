package com.yus.quran.presentation.quran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yus.quran.adapter.SurahAdapter
import com.yus.quran.databinding.ActivityDetailSurahBinding
import com.yus.quran.network.quran.SurahItem

class DetailSurahActivity : AppCompatActivity() {
    private var _binding: ActivityDetailSurahBinding? = null
    private val binding get() = _binding as ActivityDetailSurahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra(EXTRA_DATA, SurahItem::class.java)

        val quranViewModel = ViewModelProvider(this)[QuranViewModel::class.java]
        data?.number?.let { quranViewModel.getListAyah(it) }

        binding.apply {
            val revelationType = data?.revelationType
            val numberOfAyahs = data?.numberOfAyahs
            val resultOfAyah = "$revelationType - $numberOfAyahs Ayahs"
            tvDetailAyah.text = resultOfAyah
            tvDetailName.text = data?.name
            tvDetailSurah.text = data?.englishName
            tvDetailNameTranslation.text = data?.englishNameTranslation
        }

        quranViewModel.listAyah.observe(this) {
            binding.rvSurah.apply {
                val mAdapter = SurahAdapter()
                mAdapter.setData(it.quranEdition?.get(0)?.listAyahs, it.quranEdition)
                adapter = mAdapter
                layoutManager = LinearLayoutManager(this@DetailSurahActivity)
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "number"
    }
}