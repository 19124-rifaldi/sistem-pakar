package com.example.sistempakarikannila.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sistempakarikannila.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}