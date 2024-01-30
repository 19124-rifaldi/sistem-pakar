package com.example.sistempakarikannila.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.sistempakarikannila.databinding.ActivityMainBinding
import com.example.sistempakarikannila.model.Db
import com.example.sistempakarikannila.model.Gejala
import com.example.sistempakarikannila.model.PenyakitHasil
import com.example.sistempakarikannila.model.PenyakitX
import com.example.sistempakarikannila.ui.home.HomeActivity
import com.example.sistempakarikannila.ui.select_symptom.SelectSymptomActivity
import com.google.firebase.database.*
import dagger.hilt.android.AndroidEntryPoint
import com.example.sistempakarikannila.utils.Result
import java.text.DecimalFormat
import kotlin.text.StringBuilder

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<DiagnosaViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        diagnosaUlang()
        backToHome()

        var gejalaUser : List<Gejala> = mutableListOf()
        val gejalaDataList: List<Gejala>? = intent.getParcelableArrayListExtra("nilai")
        if (gejalaDataList!=null){
            gejalaUser = gejalaDataList
        }
        viewModel.penyakitList.observe(this){result ->
            when(result){
                is Result.Success ->{
                    val hasilPerkalian: MutableList<Db> = mutableListOf()
                    //perulangan gabungan nilai pakar dan user
                    for(i in result.data){
                        for ( pakar in i.gejala){
                            val userGej = gejalaUser.find { it.namaGejala==pakar.namaGejala }
                            val nilaiCertainty = if(userGej!= null) pakar.nilaiCertainty * userGej.nilaiCertainty
                            else pakar.nilaiCertainty*0.0
                            hasilPerkalian.add(Db(i.namaPenyakit,pakar.namaGejala,nilaiCertainty))
                        }
                    }
                    val hasilAkhir: MutableList<PenyakitHasil> = mutableListOf()
                    //perulangan nilai kombinasi gejala CF
                    for (penyakit in result.data) {
                        var totalCF = 0.0
                        var multiplicationFactor = 1.0

                        for (gejalaDB in penyakit.gejala) {
                            val gejala = hasilPerkalian.find { it.namaGejala == gejalaDB.namaGejala }
                            if (gejala != null && gejala.nilaiCertainty > 0.0) {
                                totalCF += gejala.nilaiCertainty * multiplicationFactor
                                multiplicationFactor *= (1 - gejala.nilaiCertainty)
                            }
                        }
                        val hasilPenyakit = PenyakitHasil(penyakit.namaPenyakit, totalCF*100,penyakit.penanganan)
                        hasilAkhir.add(hasilPenyakit)
                    }
                    getFinal(hasilAkhir)
                    getUserSymptom(gejalaUser)
                    getAllFinalDisease(hasilAkhir)
                }
                is Result.Failure ->{

                }
                is Result.Loading ->{

                }
            }
        }
    }
    //function menampilkan hasil diagnosa
    private fun getFinal(data : MutableList<PenyakitHasil> ){
        val hasilTerbesar = data.maxByOrNull { it.nilaiHasil }
        if (hasilTerbesar!=null){
            val namaPenyakitTerbesar = hasilTerbesar.namaPenyakit
            val decimalFormat = DecimalFormat("#.##")
            val nilaiHasilTerbesar = hasilTerbesar.nilaiHasil
            val formattedNumber = decimalFormat.format(nilaiHasilTerbesar)

            binding.percent.text = "$formattedNumber %"
            binding.disease.text = namaPenyakitTerbesar

            val penanganan = hasilTerbesar.penanganan
            val splitText = penanganan.split("-").filter { it.trim().isNotEmpty() }
            val formattedText = splitText.joinToString("\n- ")
            binding.penanganan.text = "-$formattedText"
        }
    }
    //function menampilkan gejala yg dipilih user
    private fun getUserSymptom(name : List<Gejala>){
        val nameList = mutableListOf<String>()
        val stringBuilder = StringBuilder()
        for (i in name){
            nameList.add(i.namaGejala)
        }
        for(a in nameList){
            stringBuilder.append("- ${a}\n")
        }
        binding.userSymptom.text = stringBuilder.toString()
    }

    private fun getAllFinalDisease(data : MutableList<PenyakitHasil>){
        val stringBuilder = StringBuilder()
        for(i in data){
            val decimalFormat = DecimalFormat("#.##")
            val nilai = i.nilaiHasil
            val formattedNumber = decimalFormat.format(nilai)
            stringBuilder.append("- penyakit ${i.namaPenyakit} : ${formattedNumber} %\n")
        }
        binding.ratioDisease.text = stringBuilder.toString()

    }
    private fun diagnosaUlang(){
        binding.diagnoseAgBt.setOnClickListener {
            val intent = Intent(this,SelectSymptomActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun backToHome(){
        binding.backHome.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}