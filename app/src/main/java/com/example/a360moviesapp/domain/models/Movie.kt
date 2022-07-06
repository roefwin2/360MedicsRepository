package com.example.a360moviesapp.domain.models

import com.example.a360moviesapp.data.models.Rating

data class Movie(
    val poster: String,
    val ratings: List<Rating>,
    val title: String,
    val plot: String,
)
