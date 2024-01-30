package com.example.sistempakarikannila.ui.select_symptom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sistempakarikannila.databinding.ActivitySelectSymptompBinding
import com.example.sistempakarikannila.ui.usercf.UserCfActivity
import com.example.sistempakarikannila.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectSymptomActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectSymptompBinding
    private val viewModel by viewModels<SymptomViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectSymptompBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle("Diagnosa Penyakit")
        viewModel.listGejala.observe(this){result ->
            when(result){
                is Result.Success ->{
                    val data = result.data
                    val recyclerView : RecyclerView = binding.recycleSymptom
                    val adapter = GejalaAdapter(data)
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager= LinearLayoutManager(this)
                    binding.selectButton.setOnClickListener {
                        val userSymptom = adapter.getSelectedItems()
                        val intent = Intent(this,UserCfActivity::class.java)
                        intent.putStringArrayListExtra("usergejala",userSymptom)
                        Log.i("usergejala","$userSymptom")
                        startActivity(intent)
                    }
                }
                is Result.Failure ->{
                    Toast.makeText(this,"Data gejala tidak ada",Toast.LENGTH_SHORT).show()
                }
                is Result.Loading ->{

                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}