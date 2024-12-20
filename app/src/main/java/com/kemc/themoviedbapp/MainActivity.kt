package com.kemc.themoviedbapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kemc.themoviedbapp.screen.MoviesList
import com.kemc.themoviedbapp.viewmodel.MoviesViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val moviesState = viewModel.movies.collectAsState()
                val isLoading = viewModel.isLoading.collectAsState()

                Box(modifier = Modifier.fillMaxSize()) {
                    if (isLoading.value) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(48.dp)
                        )
                    } else {
                        MoviesList(
                            movies = moviesState.value,
                            onMovieClick = { movie ->
                                Toast.makeText(this@MainActivity, movie.title, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }
    }
}
