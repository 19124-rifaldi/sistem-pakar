package com.example.sistempakarikannila.ui.usercf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sistempakarikannila.databinding.ActivityUserCfBinding
import com.example.sistempakarikannila.model.Gejala
import com.example.sistempakarikannila.ui.main.MainActivity
import com.google.android.material.chip.Chip

class UserCfActivity : AppCompatActivity() {
    private val gejalaList: MutableList<Gejala> = mutableListOf()
    private lateinit var binding : ActivityUserCfBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserCfBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.getStringArrayListExtra("usergejala")
        val recyclerView : RecyclerView = binding.recycleUsercf
        val adapter = UserCfAdapter(data!!)
        recyclerView.adapter = adapter
        recyclerView.layoutManager= LinearLayoutManager(this)
        binding.buttonDiagnosa.setOnClickListener {
            val cf = adapter.getSelectedValues()
            if (!adapter.isAnyChipSelected()){
                Toast.makeText(this,"Pilih kepastian terlebih dulu",Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this,MainActivity::class.java)
                intent.putParcelableArrayListExtra("nilai",ArrayList(cf))
                startActivity(intent)
                finish()
            }
        }
    }
}