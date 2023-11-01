package com.yus.quran.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yus.quran.core.di.Injection.provideQuranRepository
import com.yus.quran.presentation.quran.QuranViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(QuranViewModel::class.java) -> {
                QuranViewModel(provideQuranRepository()) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
    }
}