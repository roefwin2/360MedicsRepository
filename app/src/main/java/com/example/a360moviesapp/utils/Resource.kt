package com.example.a360moviesapp.utils

sealed class Resource<out T>(open val data : T?)
data class Loading<T>(override val data: T? = null) : Resource<T>(data)
data class Success<T>(override val data: T) : Resource<T>(data)
data class Error<T>(val msg : String ,override val data: T? = null) : Resource<T>(data)
