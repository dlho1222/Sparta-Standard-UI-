package com.example.youtubeui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.youtubeui.databinding.FragmentSubcribeBinding

class SubscribeFragment:Fragment() {
    private lateinit var binding : FragmentSubcribeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubcribeBinding.inflate(inflater)
        return binding.root
    }

}