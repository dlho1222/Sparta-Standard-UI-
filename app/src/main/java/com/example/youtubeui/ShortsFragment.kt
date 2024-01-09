package com.example.youtubeui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.youtubeui.databinding.FramentShortsBinding
import com.google.android.material.chip.ChipGroup

class ShortsFragment : Fragment() {
    private lateinit var binding: FramentShortsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FramentShortsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this).load(R.drawable.background).into(binding.ivBackground)
    }

    override fun onStart() {
        super.onStart()
        //toolbar , chipgroup 안보이게
        (activity as AppCompatActivity).supportActionBar?.hide()
        activity?.findViewById<ChipGroup>(R.id.chipGroup).let { chipGroup ->
            chipGroup?.isVisible = false
        }
    }

    override fun onDestroy() {
        //destroy 될때 다시 보여줌
        (activity as AppCompatActivity).supportActionBar?.show()
        super.onDestroy()
    }
}