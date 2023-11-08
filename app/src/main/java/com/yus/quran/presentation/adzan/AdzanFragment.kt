package com.yus.quran.presentation.adzan

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yus.quran.databinding.FragmentAdzanBinding
import com.yus.quran.presentation.SharedViewModel
import com.yus.quran.presentation.ViewModelFactory
import java.util.Locale

class AdzanFragment : Fragment() {
    private var _binding: FragmentAdzanBinding? = null
    private val binding get() = _binding as FragmentAdzanBinding

    private val sharedViewModel: SharedViewModel by viewModels { ViewModelFactory(requireContext()) }

    private var _resultOfCity: String? = null
    private val resultOfCity get() = _resultOfCity as String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdzanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.requestLocationUpdates()
        sharedViewModel.getLastKnownLocation.observe(viewLifecycleOwner) {
            if (it != null) {
                val geocoder = Geocoder(requireContext(), Locale.getDefault())
                geocoder.getFromLocation(
                    it.latitude,
                    it.longitude,
                    1
                ) { listAddress ->
                    val city = listAddress[0].subAdminArea
                    val cityListName = city.split(" ")
                    _resultOfCity =
                        if (cityListName.size > 1) cityListName[1]
                        else cityListName[0]
                    val subLocality = listAddress[0].subLocality
                    val locality = listAddress[0].locality
                    val address = "$subLocality, $locality"
                    binding.tvLocation.text = address
                    Log.i("AdzanFragment", "City Name: $resultOfCity")
                }
            } else {
                Toast.makeText(context, "Sorry, something wrong.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}