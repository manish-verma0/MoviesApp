package com.manish.moviesapp.view.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.manish.moviesapp.R
import com.manish.moviesapp.databinding.ActivityMovieDetailBinding
import com.manish.moviesapp.model.data.Movie
import com.manish.moviesapp.view.uistate.UIState
import com.manish.moviesapp.viewmodel.MovieDetailVieModel
import com.manish.moviesapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewmodel: MovieDetailVieModel by lazy {
        ViewModelProvider(this)[MovieDetailVieModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startCollecting()
        if (intent != null) {
            intent.getStringExtra("ID")?.let { viewmodel.getMovie(it) }

        }
    }

    private fun startCollecting() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.state.collect {
                    when (it) {
                        is UIState.Success -> {
                            loadData(it.data)
                        }

                        is UIState.Failure -> {
                            Log.e("MOVIES_TAG", it.throwable.toString())
                        }

                        UIState.Loading -> {

                        }
                    }
                }
            }
        }
    }

    private fun loadData(movie: Movie) {

        binding.apply {
            title.text = movie.title
            Glide.with(this@MovieDetailActivity).load(movie.poster).into(movieImage)
        }

    }


}