package com.kemc.themoviedbapp.data.network

import com.kemc.themoviedbapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("3/discover/movie") // Ruta relativa
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("sort_by") sortBy: String = "popular.desc",
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("page") page: Int = 1
    ): MovieResponse
}

