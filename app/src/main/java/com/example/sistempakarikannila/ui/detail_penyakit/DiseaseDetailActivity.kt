package com.example.sistempakarikannila.ui.detail_penyakit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.sistempakarikannila.databinding.ActivityDiseaseDetailBinding
import com.example.sistempakarikannila.ui.listPenyakit.ListPenyakitViewModel
import com.example.sistempakarikannila.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiseaseDetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDiseaseDetailBinding
    private val viewModel by viewModels<ListPenyakitViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiseaseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data= intent.getStringExtra("diseaseName")

        viewModel.penyakitList.observe(this){result ->
            when(result){
                is Result.Success -> {
                    for (i in result.data) {
                        if (data == i.namaPenyakit ){
                            val name = i.namaPenyakit
                            val penanganan = i.penanganan
                            var symptom1: MutableList<String> = mutableListOf()
                            for( gejala in i.gejala){
                                symptom1.add(gejala.namaGejala)
                            }
                            //nama
                            binding.namaPenyakit.text = "Nama Penyakit : $name"
                            //split text penanganan
                            val splitText = penanganan.split(" -").filter { it.trim().isNotEmpty() }
                            val formattedText = splitText.joinToString("\n- ")
                            binding.penanganan1.text = formattedText
                            //gejala
                            val stringBuilder = StringBuilder()
                            for(a in symptom1){
                                stringBuilder.append("- ${a}\n")
                            }
                            binding.detailGejala.text = stringBuilder.toString()
                        }
                    }
                }
                is Result.Loading-> {

                }
                is Result.Failure -> {
                    Toast.makeText(this,"kamu sendang offline,gunakan internet untuk menggunakan fitur",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}