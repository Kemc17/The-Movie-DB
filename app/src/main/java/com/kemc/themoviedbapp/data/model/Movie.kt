package com.kemc.themoviedbapp.data.model

data class Movie( val id: Int, val title: String, val overview: String, val posterPath: String )

data class MovieResponse( val results: List<Movie>)
