package com.kemc.themoviedbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kemc.themoviedbapp.screen.MoviesList
import com.kemc.themoviedbapp.ui.components.CustomSnackbar
import com.kemc.themoviedbapp.viewmodel.MoviesViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = SnackbarHostState()
            val coroutineScope = rememberCoroutineScope()

            MaterialTheme {
                val moviesState = viewModel.movies.collectAsState()
                val isLoadingState = viewModel.isLoading.collectAsState()
                val movies = moviesState.value
                val isLoading = isLoadingState.value

                Scaffold(
                    snackbarHost = {
                        SnackbarHost(
                            hostState = snackbarHostState,
                            snackbar = { snackbarData ->
                                CustomSnackbar(message = snackbarData.visuals.message)
                            }
                        )
                    }
                ) { padding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(64.dp)
                            )
                        } else {
                            MoviesList(
                                movies = movies,
                                onMovieClick = { movie ->
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = movie.title
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
