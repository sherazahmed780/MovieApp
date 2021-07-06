package com.example.movieapp.fragments

import android.content.res.Configuration
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.adapters.MovieAdapter

import com.example.movieapp.viewModels.HomeMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MoviesListFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private var noOfColumns: Int = 2
    private lateinit var movieAdapter: MovieAdapter

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView

        val viewModel =
            ViewModelProvider(requireActivity()).get(HomeMoviesViewModel::class.java)

        movieAdapter = activity?.let { MovieAdapter(it) }!!

        recyclerView.apply {
            recyclerView.layoutManager = GridLayoutManager(activity, noOfColumns)

            //  recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = movieAdapter
        }


        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.movies.collectLatest { data ->

                movieAdapter.submitData(data)

            }
        }


        getScreenOrientation()

        return view
    }

    private fun getScreenOrientation(): String {
        var orientation = "undefined"
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            orientation = "landscape"
            noOfColumns = 3
            recyclerView.layoutManager = GridLayoutManager(activity, noOfColumns)

        } else if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            orientation = "portrait"
            noOfColumns = 2
            recyclerView.layoutManager = GridLayoutManager(activity, noOfColumns)
        }
        return orientation
    }


}