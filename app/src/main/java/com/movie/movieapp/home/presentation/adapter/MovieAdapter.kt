package com.movie.movieapp.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.movie.movieapp.databinding.ItemMoviesBinding
import com.movie.movieapp.home.data.dto.MovieItem

class MovieAdapter(private val listener: OnMovieItemClick) :
    ListAdapter<MovieItem, RecyclerView.ViewHolder>(DiffMovieCallBack) {

    interface OnMovieItemClick {
        fun onMovieItemClick(movie: MovieItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieViewHolder).bind(getItem(position))
    }

    private inner class MovieViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieItem) {
            binding.apply {
                img.load("https://picsum.photos/id/0/5000/3333")
                title.text = movie.title
                content.text = movie.desc
                root.setOnClickListener {
                    listener.onMovieItemClick(movie)
                }
            }
        }
    }
}

object DiffMovieCallBack : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(
        oldItem: MovieItem,
        newItem: MovieItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MovieItem,
        newItem: MovieItem
    ): Boolean {
        return oldItem == newItem
    }
}