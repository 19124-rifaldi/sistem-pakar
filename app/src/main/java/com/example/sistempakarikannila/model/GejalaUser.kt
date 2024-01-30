package com.example.sistempakarikannila.model

data class Db(val penyakit : String,val namaGejala: String, val nilaiCertainty: Double)

data class PenyakitHasil(
    val namaPenyakit: String,
    val nilaiHasil: Double,
    val penanganan : String
)
