package com.movie.movieapp.detail.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.movie.movieapp.R
import com.movie.movieapp.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private lateinit var binding: FragmentMovieDetailsBinding
    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)
        setDataToUi()
        setListeners()
    }

    private fun setListeners(){
        binding.apply {
            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun setDataToUi() {
        binding.apply {
            imageView.load("https://picsum.photos/id/0/5000/3333")
            title.text = args.title
            rating.text = args.rating
            content.text = args.content
            releaseDate.text = args.releaseDate
        }
    }
}