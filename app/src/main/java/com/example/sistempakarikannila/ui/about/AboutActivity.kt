package com.example.sistempakarikannila.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sistempakarikannila.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textView6.text = "Dibuat oleh : \n Rifaldi Febrianto\n Mahasiswa Informatika\n" +
                "Universitas Singaperbangsa Karawang"
        binding.textView7.text = "Data didapat dari :\n BLUPPB Karawang"
        binding.textView8.text = "Sistem Pakar Ikan Nila"
    }
}