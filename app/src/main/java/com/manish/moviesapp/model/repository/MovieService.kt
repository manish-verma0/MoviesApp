package com.manish.moviesapp.model.repository

import com.manish.moviesapp.model.data.Movie
import com.manish.moviesapp.model.data.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/")
    suspend fun getMovies(
        @Query("apikey") key: String,
        @Query("s") searchString: String,
        @Query("page") page: Int
    ): Response<Movies>

    @GET("/")
    suspend fun getMovie(
        @Query("apikey") key: String,
        @Query("i") id: String
    ): Response<Movie>
}