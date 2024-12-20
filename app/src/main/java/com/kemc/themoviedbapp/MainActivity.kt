package com.kemc.themoviedbapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kemc.themoviedbapp.data.network.RetrofitClient
import com.kemc.themoviedbapp.ui.screen.MoviesList
import com.kemc.themoviedbapp.ui.theme.TheMovieDBAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.kemc.themoviedbapp.viewmodel.MoviesViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val moviesState = viewModel.movies.collectAsState()
                val movies = moviesState.value

                MoviesList(movies = movies)
            }
        }
    }
}


