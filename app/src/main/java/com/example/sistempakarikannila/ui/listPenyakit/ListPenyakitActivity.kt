package com.example.sistempakarikannila.ui.listPenyakit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.sistempakarikannila.databinding.ActivityListPenyakitBinding
import com.example.sistempakarikannila.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListPenyakitActivity : AppCompatActivity() {
    private lateinit var binding : ActivityListPenyakitBinding
    private val viewModel by viewModels<ListPenyakitViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListPenyakitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.penyakitList.observe(this){result ->
            when(result){
                is Result.Success ->{
                    val dataList = arrayListOf<String>()
                    for (i in result.data){
                        dataList.add(i.namaPenyakit)
                    }
                    getAdapter(dataList)
                }
                is Result.Loading -> {

                }
                is Result.Failure ->{

                }
            }
        }
    }
    private fun getAdapter(data:ArrayList<String>){
        val recyclerView : RecyclerView = binding.recycleDiseaseList
        val adapter = ListPenyakitAdapter(data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager= LinearLayoutManager(this)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}