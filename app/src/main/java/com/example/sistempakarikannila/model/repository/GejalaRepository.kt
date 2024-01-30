package com.example.sistempakarikannila.model.repository

import com.example.sistempakarikannila.utils.Result

interface GejalaRepository {
    suspend fun getGejala(result:(Result<List<String>>)-> Unit)
}