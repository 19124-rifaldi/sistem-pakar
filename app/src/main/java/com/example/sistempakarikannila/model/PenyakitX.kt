package com.example.sistempakarikannila.model

data class PenyakitX(
    var gejala: List<Gejala> = emptyList(),
    var idPenyakit: String = "",
    var namaPenyakit: String = "",
    var penanganan : String = ""
)