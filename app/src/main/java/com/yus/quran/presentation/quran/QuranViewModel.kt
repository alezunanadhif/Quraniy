package com.yus.quran.presentation.quran

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yus.quran.network.ApiConfig
import com.yus.quran.network.quran.SurahResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuranViewModel : ViewModel() {
    private var _listSurah = MutableLiveData<SurahResponse>()
    val listSurah get() = _listSurah as LiveData<SurahResponse>

    fun getListSurah() {
        ApiConfig.getQuranService.getListSurah().enqueue(object : Callback<SurahResponse> {
            override fun onResponse(call: Call<SurahResponse>, response: Response<SurahResponse>) {
                if (response.isSuccessful) {
                    Log.i("QuranViewModel", "onResponse: ${response.body()}")
                    _listSurah.postValue(response.body())
                } else Log.e(
                    "QuranViewModel",
                    "onResponse: Call error with Http status code " + response.code(),
                )
            }

            override fun onFailure(call: Call<SurahResponse>, t: Throwable) {
                Log.e("QuranViewModel", "onFailure: " + t.localizedMessage )
            }

        })
    }
}