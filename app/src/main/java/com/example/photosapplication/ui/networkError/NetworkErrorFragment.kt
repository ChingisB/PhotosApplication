package com.example.photosapplication.ui.networkError

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.photosapplication.databinding.FragmentNetworkErrorBinding

class NetworkErrorFragment : Fragment() {

    private lateinit var binding: FragmentNetworkErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNetworkErrorBinding.inflate(inflater)
        return binding.root
    }

}