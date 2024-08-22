package com.manish.moviesapp.view.activities

import android.content.Intent
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
import com.manish.moviesapp.R
import com.manish.moviesapp.core.utils.ClickCallback
import com.manish.moviesapp.databinding.ActivityMainBinding
import com.manish.moviesapp.model.data.Movie
import com.manish.moviesapp.view.adapter.MovieAdapter
import com.manish.moviesapp.view.uistate.UIState
import com.manish.moviesapp.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ClickCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    private val viewmodel: MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startCollecting()
        setListeners()
    }

    private fun setListeners() {
        binding.searchButton.setOnClickListener {
            if(!binding.searchEt.text.isNullOrEmpty()) {
                viewmodel.getMovies(binding.searchEt.text.toString())
            }
        }
    }

    private fun startCollecting() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.state.collect{
                    when(it) {
                        is UIState.Success -> {
                            loadData(it.data.search)
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

    private fun loadData(movieList: List<Movie>) {
        adapter = MovieAdapter(movieList, this)
        binding.movieReccler.adapter = adapter

    }

    override fun onClick(id: String) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putString("ID", id)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}