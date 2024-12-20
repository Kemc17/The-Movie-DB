package com.kemc.themoviedbapp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kemc.themoviedbapp.data.model.Movie
import com.kemc.themoviedbapp.ui.screen.MovieItem

@Composable
fun MoviesList(movies: List<Movie>, onMovieClick: (Movie) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(movies) { movie ->
            MovieItem(movie = movie, onClick = { onMovieClick(movie) })
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
