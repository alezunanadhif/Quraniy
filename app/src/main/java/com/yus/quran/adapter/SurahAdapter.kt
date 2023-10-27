package com.yus.quran.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yus.quran.databinding.ItemAyahBinding
import com.yus.quran.network.quran.AyahsItem
import com.yus.quran.network.quran.QuranEdition

class SurahAdapter : RecyclerView.Adapter<SurahAdapter.MyViewHolder>() {
    private val listAyah = ArrayList<AyahsItem>()
    private val listEdition = ArrayList<QuranEdition>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(dataAyah: List<AyahsItem>?, dataEdition: List<QuranEdition>?) {
        if (dataAyah == null || dataEdition == null) return
        listAyah.clear()
        listAyah.addAll(dataAyah)
        listEdition.clear()
        listEdition.addAll(dataEdition)
    }

    class MyViewHolder(val binding: ItemAyahBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemAyahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listAyah = listAyah[position]
        val quranAudio = listEdition[1].listAyahs?.get(position)
        val quranTranslationIndo = listEdition[2].listAyahs?.get(position)

        holder.binding.apply {
            itemNumberAyah.text = listAyah.numberInSurah.toString()
            itemAyah.text = listAyah.text
            itemTranslation.text = quranTranslationIndo?.text
            this.root.setOnClickListener {
                quranAudio?.let { data -> onItemClickCallback?.onItemClicked(data) }
            }
        }
    }

    override fun getItemCount() = listAyah.size

    interface OnItemClickCallback {
        fun onItemClicked(data: AyahsItem)
    }
}