package com.example.youtubeui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat

class SearchActivity : AppCompatActivity() {
    //request code
    companion object {
        private const val REQUEST_READ_MEDIA_IMAGES_CODE = 100
    }

    //
    private val loadImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            changeImage(uriList)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)
        val loadImageButton = findViewById<Button>(R.id.btn_LoadImage)
        toolBar.apply {
            title = "SearchActivity"
            setSupportActionBar(this)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadImageButton.setOnClickListener {
            checkPermission()
        }

    }



    //선택한 uriList 를 image로 세팅
    private fun changeImage(uriList: List<Uri>) {
        val image = findViewById<ImageView>(R.id.iv_Image)
        image.setImageURI(uriList[0])
    }
    //갤러리 접근
    private fun loadImage() {
        loadImageLauncher.launch("image/*")
    }

    //권한 요청
    private fun requestReadMediaImages() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
            REQUEST_READ_MEDIA_IMAGES_CODE
        )
    }
    //권한 확인
    private fun checkPermission() {
        when {
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_AUDIO
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadImage()
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_MEDIA_AUDIO
            ) -> {
                showRequestRationalDialog()
            }

            else -> requestReadMediaImages()
        }
    }


    private fun showRequestRationalDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("사진을 가져오려면 권한이 필요합니다.")
            setNegativeButton("취소") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            setPositiveButton("확인") { _, _ -> requestReadMediaImages() }
            show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    //요청된 권한 결과 처리
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val readImagePermissionGranted =
            requestCode == REQUEST_READ_MEDIA_IMAGES_CODE && grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED
        if (readImagePermissionGranted) { //request코드 와 권한이 허용된 상태면 loadImages()
            loadImage()
        } else {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_MEDIA_IMAGES)){
                showRequestRationalDialog()  //권한이 거부되면 교육용 다이얼로그
            }
            else{
                showSettingDialog()     //다시 취소시 세팅다이얼로그
            }
        }
    }

    private fun showSettingDialog() {//셋팅 다이얼로그
        AlertDialog.Builder(this).apply {
            setMessage("권한이 취소된 상태입니다. 권한 허용 하러가기")
            setNegativeButton("취소") { dialogInterface, _ -> dialogInterface.cancel() }
            setPositiveButton("확인") { _, _ -> navigateToSettings() }
            show()
        }
    }

    private fun navigateToSettings() {   //권한이 취소된 상태에서 다이얼로그에서 확인 눌렀을 때 세팅화면으로 이동
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", packageName, null)
        }
        startActivity(intent)
    }
}