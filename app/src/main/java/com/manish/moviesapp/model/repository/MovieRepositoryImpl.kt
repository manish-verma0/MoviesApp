package com.manish.moviesapp.model.repository

import com.manish.moviesapp.core.utils.Constants
import com.manish.moviesapp.model.data.Movie
import com.manish.moviesapp.model.data.Movies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieService: MovieService) : MovieRepository {

    override fun getMovies(searchString: String): Flow<Movies> {
        return flow {
            movieService.getMovies(Constants.KEY, searchString, 1).body()?.let {
                emit(
                    it
                )
            }
        }
    }

    override fun getMovie(id: String): Flow<Movie> {
        return flow {
            movieService.getMovie(Constants.KEY, id).body()?.let {
                emit(
                    it
                )
            }
        }
    }
}