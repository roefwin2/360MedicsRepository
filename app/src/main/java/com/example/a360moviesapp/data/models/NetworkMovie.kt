package com.example.a360moviesapp.data.models


import com.squareup.moshi.Json

data class NetworkMovie(
    @Json(name = "Genre")
    val genre: String,
    @Json(name = "Plot")
    val plot: String,
    @Json(name = "Poster")
    val poster: String,
    @Json(name = "Rated")
    val rated: String,
    @Json(name = "Ratings")
    val ratings: List<Rating>,
    @Json(name = "Title")
    val title: String,
    @Json(name = "Type")
    val type: String,
    @Json(name = "Website")
    val website: String,
    @Json(name = "Year")
    val year: String,
    @Json(name = "Response")
    val response: String
)