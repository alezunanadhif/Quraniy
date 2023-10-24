package com.yus.quran.presentation.quran

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yus.quran.adapter.QuranAdapter
import com.yus.quran.databinding.FragmentQuranBinding

class QuranFragment : Fragment() {

    private var _binding : FragmentQuranBinding? = null
    private val binding get() = _binding as FragmentQuranBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuranBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[QuranViewModel::class.java]
        viewModel.getListSurah()

        viewModel.listSurah.observe(viewLifecycleOwner) {
            binding.rvQuran.apply {
                val mAdapter = QuranAdapter()
                mAdapter.setData(it.listSurah)
                adapter = mAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}