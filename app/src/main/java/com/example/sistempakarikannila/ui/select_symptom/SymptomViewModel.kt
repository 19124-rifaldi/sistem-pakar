package com.example.sistempakarikannila.ui.select_symptom

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistempakarikannila.model.repository.GejalaRepository
import com.example.sistempakarikannila.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SymptomViewModel @Inject constructor(
    private val gejalaRepository: GejalaRepository
):ViewModel() {
    private val _listGejala = MutableLiveData<Result<List<String>>>()
    val listGejala : LiveData<Result<List<String>>>
    get() = _listGejala
    init {
        fetchGejala()
    }
    private fun fetchGejala(){
        viewModelScope.launch {
            try {
                gejalaRepository.getGejala {
                    _listGejala.value = it
                }
            }catch (e:Exception){
                Log.i("gagal","$e")
            }
        }
    }

}