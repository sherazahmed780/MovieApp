package com.example.movieapp.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.movieapp.R
import com.example.movieapp.adapters.MovieAdapter
import com.example.movieapp.listeners.MovieItemClickListener
import com.example.movieapp.model.homeMovies.Results

import com.example.movieapp.viewModels.HomeMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MoviesListFragment : Fragment(), MovieItemClickListener {


    private lateinit var recyclerView: RecyclerView
    private var noOfColumns: Int = 2
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var itemClick: MovieItemClickListener
    private lateinit var searchEditText: EditText

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        itemClick = this

        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        searchEditText = view.findViewById(R.id.search_et) as EditText

        val viewModel =
            ViewModelProvider(requireActivity()).get(HomeMoviesViewModel::class.java)

        movieAdapter = activity?.let { MovieAdapter(itemClick, it) }!!

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


        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {



            }
            false
        }
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


    override fun onMovieClick(view: View?, position: Int, movieData: Results) {
        if (view != null) {

            val bundle = Bundle()
            val movieID = movieData.id.toString()

            bundle.putString("movieID", movieID)

            view.findNavController()
                .navigate(R.id.action_moviesListFragment_to_singleMovieDetailFragment, bundle)

        }

    }

}