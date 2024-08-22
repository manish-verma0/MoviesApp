package com.manish.moviesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manish.moviesapp.model.data.Movies
import com.manish.moviesapp.model.repository.MovieRepositoryImpl
import com.manish.moviesapp.view.uistate.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepositoryImpl: MovieRepositoryImpl) : ViewModel() {

    private val _state = MutableStateFlow<UIState<Movies>>(UIState.Loading)
    val state = _state.asStateFlow()

    fun getMovies(searchString: String) {
        viewModelScope.launch {
            movieRepositoryImpl.getMovies(searchString)
                .flowOn(Dispatchers.IO)
                .catch {
                    _state.emit(UIState.Failure(it))
                }
                .collect{
                    _state.emit(UIState.Success(it))
                }
        }

    }
}