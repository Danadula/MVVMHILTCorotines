package com.mano.movieslistapp

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mano.movieslistapp.adapter.MovieAdapter
import com.mano.movieslistapp.databinding.ActivityMainBinding
import com.mano.movieslistapp.viewModels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter()

        binding.recyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        binding.rvEpisodes.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        binding.rvRecentlyAdded.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL,
                false
            )

            setHasFixedSize(true)
        }

        viewModel.responseMoviesList.observe(this) { listTvShows ->
            if (listTvShows.isNotEmpty()) {
                binding.apply {
                    progressCircular1.visibility = View.GONE
                    progressCircular2.visibility = View.GONE
                    progressCircular3.visibility = View.GONE

                }
                movieAdapter.movieShowItem = listTvShows
            }
        }

    }
}