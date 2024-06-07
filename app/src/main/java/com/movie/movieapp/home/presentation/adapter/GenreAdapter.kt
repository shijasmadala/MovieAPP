package com.movie.movieapp.home.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.movie.movieapp.databinding.ItemGenereBinding
import com.movie.movieapp.home.data.dto.HomeData

class GenreAdapter(private val itemClick : MovieAdapter.OnMovieItemClick) : ListAdapter<HomeData, RecyclerView.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemGenereBinding.inflate(LayoutInflater.from(parent.context) , parent ,false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GenreViewHolder).bind(getItem(position))
    }

    private inner class GenreViewHolder(private val binding: ItemGenereBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(homeData: HomeData){
                val movieAdapter = MovieAdapter(itemClick)
                binding.apply {
                    count.text = homeData.id.plus(".")
                    title.text = homeData.genre
                    recyclerView.adapter = movieAdapter
                    movieAdapter.submitList(homeData.movieslist)
                }
            }
        }
}

object DiffCallBack : DiffUtil.ItemCallback<HomeData>() {
    override fun areItemsTheSame(
        oldItem: HomeData,
        newItem: HomeData
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: HomeData,
        newItem: HomeData
    ): Boolean {
        return oldItem == newItem
    }
}