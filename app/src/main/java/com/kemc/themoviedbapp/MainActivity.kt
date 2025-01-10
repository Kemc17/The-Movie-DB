package com.kemc.themoviedbapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.kemc.themoviedbapp.data.network.RetrofitClient.apiService
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
            val context = LocalContext.current

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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        // Estado de carga o lista de películas
                        if (isLoading) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f), // Llenar el espacio disponible
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(64.dp)
                                )
                            }
                        } else {
                            MoviesList(
                                movies = movies,
                                modifier = Modifier.weight(1f), // Llenar el espacio disponible
                                onMovieClick = { movie ->
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = movie.title
                                        )
                                    }
                                }
                            )
                        }
                        // Botón de Deeplink
                        Button(
                            onClick = {
                                lifecycleScope.launch {
                                    try {

                                        val movieResponse = apiService.getPopularMovies(
                                            language = "en-US",
                                            sortBy = "popularity.desc",
                                            includeAdult = false,
                                            page = 1
                                        )

                                        val listaDePeliculas = movieResponse.results
                                        val peliculasJson = Gson().toJson(listaDePeliculas)

                                        val intent = Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("themoviedbapp://movies/list?data=${Uri.encode(peliculasJson)}")
                                        ).apply {
                                            `package` = "com.example.deeplink"
                                        }

                                        context.startActivity(intent)
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                        Toast.makeText(context, "Error al obtener las películas", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(text = "DEEPLINK")
                        }
                    }
                }
            }
        }
    }
}

