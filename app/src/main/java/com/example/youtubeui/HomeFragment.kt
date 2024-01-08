package com.example.youtubeui

import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeui.databinding.FragmentRecyclerviewBinding

class HomeFragment :Fragment(){
    private lateinit var binding : FragmentRecyclerviewBinding
    private lateinit var imageAdapter: ImageAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerviewBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }
    private fun initRecyclerView() {
        imageAdapter = ImageAdapter(object : OnClickListener {
            override fun onSpeakerClicked() {
                Toast.makeText(context, "스피커on", Toast.LENGTH_SHORT).show()
            }

            override fun onSubTitleClicked() {
                Toast.makeText(context, "자막on", Toast.LENGTH_SHORT).show()
            }

            override fun onVodClicked() {
                showDetailInfoDialog()
            }
        })
        binding.recyclerView.apply {
            adapter = imageAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
    private fun showDetailInfoDialog() {
        AlertDialog.Builder(context).apply {
            setMessage("유튜브 영상")
            setPositiveButton("확인") { _, _ -> }
            show()
        }
    }
}