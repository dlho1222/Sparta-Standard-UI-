package com.example.youtubeui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeui.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip

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
        binding.bottomNavi.apply {
            itemIconTintList = null

        }
        supportActionBar?.setLogo(R.drawable.logo)
        initViews()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        imageAdapter = ImageAdapter(object : OnClickListener {
            override fun onSpeakerClicked() {
                Toast.makeText(baseContext, "스피커on", Toast.LENGTH_SHORT).show()
            }

            override fun onSubTitleClicked() {
                Toast.makeText(baseContext, "자막on", Toast.LENGTH_SHORT).show()
            }

            override fun onVodClicked() {
                showDetailInfoDialog()
            }
        })
        binding.recyclerView.apply {
            adapter = imageAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    private fun initViews() {
        val topIndex = listOf(
            "전체",
            "음악",
            "실시간",
            "게임",
            "스케치 코미디",
            "인테리어",
            "요리 프로그램",
            "축구",
            "요리",
            "반려동물",
            "액션 어드벤처 게임",
            "공예",
            "만화 영화",
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
            background = ContextCompat.getDrawable(context, R.drawable.bg_chipgroup)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {  //돋보기 눌렀을 때 명시적 인텐트로 액티비티 전환
        return when (item.itemId) {
            R.id.action_Search -> {
                startActivity(Intent(this, SearchActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun showDetailInfoDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("유튜브 영상")
            setPositiveButton("확인") { _, _ -> }
            show()
        }

    }
}