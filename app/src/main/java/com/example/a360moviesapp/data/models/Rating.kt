package com.example.a360moviesapp.data.models


import com.squareup.moshi.Json

data class Rating(
    @Json(name = "Source")
    val source: String,
    @Json(name = "Value")
    val value: String
)