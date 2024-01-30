package com.example.sistempakarikannila.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gejala(
    var namaGejala: String = "",
    var nilaiCertainty: Double = 0.0
):Parcelable