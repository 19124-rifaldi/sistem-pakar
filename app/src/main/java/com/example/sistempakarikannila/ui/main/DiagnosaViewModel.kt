package com.example.sistempakarikannila.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.sistempakarikannila.model.Penyakit
import com.example.sistempakarikannila.model.PenyakitX
import com.example.sistempakarikannila.model.repository.PenyakitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.sistempakarikannila.utils.Result

@HiltViewModel
class DiagnosaViewModel @Inject constructor(
    private val penyakitRepository: PenyakitRepository
):ViewModel(){
    private val _penyakitList = MutableLiveData<Result<List<PenyakitX>>>()
    val penyakitList : LiveData<Result<List<PenyakitX>>>
    get() = _penyakitList
    init {
        fetchPenyakit()
    }
    fun fetchPenyakit() {
        viewModelScope.launch {
            try {
                penyakitRepository.getPenyakit{
                    _penyakitList.value = it
                }

            } catch (e: Exception) {
                Log.i("gagal","$e")
            }
        }
    }

}