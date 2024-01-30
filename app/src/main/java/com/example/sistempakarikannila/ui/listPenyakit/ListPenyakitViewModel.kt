package com.example.sistempakarikannila.ui.listPenyakit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistempakarikannila.model.PenyakitX
import com.example.sistempakarikannila.model.repository.PenyakitRepository
import com.example.sistempakarikannila.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPenyakitViewModel @Inject constructor(
    private val penyakitRepository: PenyakitRepository
):ViewModel() {
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