package com.manish.moviesapp.model.repository

import com.manish.moviesapp.model.data.Movie
import com.manish.moviesapp.model.data.Movies
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(searchString: String): Flow<Movies>

    fun getMovie(id: String): Flow<Movie>
}