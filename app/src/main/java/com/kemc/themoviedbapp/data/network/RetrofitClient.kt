package com.kemc.themoviedbapp.data.network

import com.google.gson.internal.GsonBuildConfig
import com.kemc.themoviedbapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
    private const val BASE_URL = "https://api.themoviedb.org/" //Ruta base

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(BuildConfig.TMDB_BEARER_TOKEN))
        .build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
