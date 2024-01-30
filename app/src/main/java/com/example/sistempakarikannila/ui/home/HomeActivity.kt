package com.example.sistempakarikannila.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sistempakarikannila.databinding.ActivityHomeBinding
import com.example.sistempakarikannila.ui.about.AboutActivity
import com.example.sistempakarikannila.ui.listPenyakit.ListPenyakitActivity
import com.example.sistempakarikannila.ui.select_symptom.SelectSymptomActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var backPressedTime: Long = 0
    private val exitInterval: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goToDiagnose()
        goToAbout()

        supportActionBar?.hide()
        goToDiseaseList()
    }
    private fun goToDiagnose(){
        binding.diagnoseBt.setOnClickListener {
            startActivity(Intent(this,SelectSymptomActivity::class.java))
        }
    }
    private fun goToDiseaseList(){
        binding.listBt.setOnClickListener {
            startActivity(Intent(this,ListPenyakitActivity::class.java))
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (backPressedTime + exitInterval > System.currentTimeMillis()) {
            finishAffinity()
            finish()
        } else {
            Toast.makeText(this, "Klik lagi untuk keluar", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
    private fun goToAbout(){
        binding.aboutBt.setOnClickListener {
            startActivity(Intent(this,AboutActivity::class.java))
        }
    }
}