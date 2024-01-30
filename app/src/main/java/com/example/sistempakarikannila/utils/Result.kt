package com.example.sistempakarikannila.utils

sealed class Result<out T> {
    object Loading: Result<Nothing>()
    data class Success<out T>(val data: T): Result<T>()
    data class Failure(val error: String?): Result<Nothing>()
}