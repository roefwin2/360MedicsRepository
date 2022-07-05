package com.example.a360moviesapp.utils

sealed class Resource<T>(open val data : T?)
data class Loading<T>(override val data: T? = null) : Resource<T>(data)
data class Error<T>(val msg : String ,override val data: T? = null) : Resource<T>(data)
