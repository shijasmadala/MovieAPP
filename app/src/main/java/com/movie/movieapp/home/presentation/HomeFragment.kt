package com.movie.movieapp.home.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.movie.movieapp.R
import com.movie.movieapp.databinding.FragmentHomeBinding
import com.movie.movieapp.home.data.dto.MovieItem
import com.movie.movieapp.home.presentation.adapter.GenreAdapter
import com.movie.movieapp.home.presentation.adapter.MovieAdapter
import com.movie.movieapp.utils.LoadingScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) , MovieAdapter.OnMovieItemClick {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val genreAdapter by lazy { GenreAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        observeHomeState()
        binding.recyclerView.adapter = genreAdapter
    }

    private fun observeHomeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collectLatest { homeData ->
                    when(homeData) {
                        is HomeUiState.Empty -> {

                        }
                        is HomeUiState.Error -> {
                            Toast.makeText(requireContext(), homeData.message, Toast.LENGTH_SHORT).show()
                            LoadingScreen.operateDialog(false,requireContext())
                        }
                        is HomeUiState.Loading -> {
                            LoadingScreen.operateDialog(true,requireContext())
                        }
                        is HomeUiState.Success -> {
                            LoadingScreen.operateDialog(false,requireContext())
                            genreAdapter.submitList(homeData.movieData.homeData)
                        }
                    }
                }
            }
        }
    }

    override fun onMovieItemClick(movie: MovieItem) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(
            movieImageUrl = movie.posterurl,
            title = movie.title,
            content = movie.desc,
            rating = movie.rating,
            releaseDate = movie.release
        ))
    }
}