package com.manish.moviesapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.manish.moviesapp.core.utils.ClickCallback
import com.manish.moviesapp.databinding.MovieLayourBinding
import com.manish.moviesapp.model.data.Movie

class MovieAdapter(private val movieList: List<Movie>, private val clickCallback: ClickCallback) : Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding: MovieLayourBinding) : ViewHolder(binding.root) {

        fun bind(movie: Movie, clickCallback: ClickCallback) {
            binding.apply {
                title.text = movie.title
                Glide.with(root).load(movie.poster).into(movieImage)

                root.setOnClickListener{
                    clickCallback.onClick(movie.imdbID)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieLayourBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position], clickCallback)
    }
}