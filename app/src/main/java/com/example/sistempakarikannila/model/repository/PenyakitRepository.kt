package com.example.sistempakarikannila.model.repository

import com.example.sistempakarikannila.model.Gejala
import com.example.sistempakarikannila.model.Penyakit
import com.example.sistempakarikannila.model.PenyakitX
import com.example.sistempakarikannila.utils.Result

interface PenyakitRepository{
    suspend fun getPenyakit(result: (Result<List<PenyakitX>>)-> Unit)
}

