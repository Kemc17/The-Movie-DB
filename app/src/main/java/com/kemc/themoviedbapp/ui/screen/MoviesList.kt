package com.kemc.themoviedbapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kemc.themoviedbapp.data.model.Movie

@Composable
fun MoviesList(movies: List<Movie>) {
    var selectedMovie by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(movies) { movie ->
                    MovieItem(movie = movie) {
                        selectedMovie = movie.title
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

    LaunchedEffect(selectedMovie) {
        selectedMovie?.let { movieTitle ->
            snackbarHostState.showSnackbar(movieTitle)
            selectedMovie = null
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Text(
            text = movie.title,
            maxLines = 1,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = movie.overview,
            maxLines = 3,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
