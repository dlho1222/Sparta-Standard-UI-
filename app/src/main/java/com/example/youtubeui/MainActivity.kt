package com.example.youtubeui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeui.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var imageAdapter: ImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolBar.apply {
            setSupportActionBar(this)
        }
        supportActionBar?.setLogo(R.drawable.logo)
        initViews()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        imageAdapter = ImageAdapter(object :OnClickListener{
            override fun onSpeakerClicked() {
                Toast.makeText(baseContext, "스피커", Toast.LENGTH_SHORT).show()

            }

            override fun onSubTitleClicked() {
                Toast.makeText(baseContext, "자막", Toast.LENGTH_SHORT).show()
            }

        })
        binding.recyclerView.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun initViews() {
        val topIndex = listOf(
            "전체",
            "실시간",
            "뉴스",
            "게임",
            "요리 프로그램",
            "요리",
            "반려동물",
            "액션 어드벤쳐 게임",
            "축구",
            "최근에 업로드된 동영상"
        )
        topIndex.forEach { text ->
            binding.chipGroup.addView(createChip(text))
        }
    }

    private fun createChip(text: String): Chip {
        return Chip(this).apply {
            setText(text)
            isClickable = true
            isCheckable = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {  //돋보기 눌렀을 때 명시적 인텐트로 액티비티 전환
        return when(item.itemId){
            R.id.action_Search->{
                startActivity(Intent(this,SearchActivity::class.java))
                true
            }

            else->super.onOptionsItemSelected(item)
        }

    }

}