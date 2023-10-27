package com.yus.quran.presentation.quran

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yus.quran.R
import com.yus.quran.adapter.SurahAdapter
import com.yus.quran.databinding.ActivityDetailSurahBinding
import com.yus.quran.databinding.CustomViewAlertdialogBinding
import com.yus.quran.network.quran.AyahsItem
import com.yus.quran.network.quran.SurahItem

class DetailSurahActivity : AppCompatActivity() {
    private var _binding: ActivityDetailSurahBinding? = null
    private val binding get() = _binding as ActivityDetailSurahBinding

    private var _surah: SurahItem? = null
    private val surah get() = _surah as SurahItem

    private var _mediaPlayer: MediaPlayer? = null
    private val mediaPlayer get() = _mediaPlayer as MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _surah = intent.getParcelableExtra(EXTRA_DATA, SurahItem::class.java)

        val quranViewModel = ViewModelProvider(this)[QuranViewModel::class.java]
        surah.number?.let { quranViewModel.getListAyah(it) }

        initView()

        val mAdapter = SurahAdapter()
        mAdapter.setOnItemClickCallback(object : SurahAdapter.OnItemClickCallback {
            override fun onItemClicked(data: AyahsItem) {
                showCustomAlertDialog(data, surah)
            }
        })

        quranViewModel.listAyah.observe(this) {
            binding.rvSurah.apply {
                mAdapter.setData(it.quranEdition?.get(0)?.listAyahs, it.quranEdition)
                adapter = mAdapter
                layoutManager = LinearLayoutManager(this@DetailSurahActivity)
            }
        }
    }

    private fun initView() {
        binding.apply {
            val revelationType = surah.revelationType
            val numberOfAyahs = surah.numberOfAyahs
            val resultOfAyah = "$revelationType - $numberOfAyahs Ayahs"
            tvDetailAyah.text = resultOfAyah
            tvDetailName.text = surah.name
            tvDetailSurah.text = surah.englishName
            tvDetailNameTranslation.text = surah.englishNameTranslation
        }
    }

    private fun showCustomAlertDialog(dataAudio: AyahsItem, surah: SurahItem) {
        _mediaPlayer = MediaPlayer()
        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog).create()
        val view = CustomViewAlertdialogBinding.inflate(layoutInflater)
        builder.setView(view.root)
        view.apply {
            tvSurah.text = surah.englishName
            tvName.text = surah.name
            val numberInSurah = dataAudio.numberInSurah
            val resultNumberText = "Ayah $numberInSurah"
            tvNumberAyah.text = resultNumberText
        }
        view.btnPlay.setOnClickListener {
            it.isEnabled = false
            view.btnPlay.text = getString(R.string.playing_audio)
            mediaPlayer.setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            try {
                mediaPlayer.setDataSource(dataAudio.audio)
                mediaPlayer.prepare()
                mediaPlayer.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        view.btnCancel.setOnClickListener {
            mediaPlayer.stop()
            builder.dismiss()
        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()
        mediaPlayer.setOnCompletionListener {
            builder.dismiss()
        }
    }

    companion object {
        const val EXTRA_DATA = "number"
    }
}