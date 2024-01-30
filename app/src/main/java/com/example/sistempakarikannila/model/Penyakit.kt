package com.example.sistempakarikannila.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Penyakit(
    var penyakit: List<PenyakitX> = emptyList()
)