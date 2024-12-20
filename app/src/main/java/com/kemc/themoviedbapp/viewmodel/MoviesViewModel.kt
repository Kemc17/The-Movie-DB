package com.kemc.themoviedbapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kemc.themoviedbapp.data.model.Movie
import com.kemc.themoviedbapp.data.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getPopularMovies()
                _movies.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}