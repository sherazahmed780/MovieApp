package com.example.movieapp.fragments


import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.adapters.MovieAdapter
import com.example.movieapp.databinding.FragmentSearchMovieBinding
import com.example.movieapp.listeners.BackBtnListener
import com.example.movieapp.listeners.MovieItemClickListener
import com.example.movieapp.model.homeMovies.Results
import com.example.movieapp.utils.MyProgressBar
import com.example.movieapp.utils.checkConnection
import com.example.movieapp.viewModels.SearchMovieViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class SearchMovieFragment : Fragment(), MovieItemClickListener,BackBtnListener {

    private lateinit var recyclerView: RecyclerView
    private var noOfColumns: Int = 2
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var itemClick: MovieItemClickListener

    private lateinit var binding: FragmentSearchMovieBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // val view = inflater.inflate(R.layout.fragment_search_movie, container, false)
        binding =
            FragmentSearchMovieBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val queryName: String = arguments?.getString("queryName")!!

        itemClick = this
        binding.clickHandler = this

        recyclerView = binding.recyclerView

        val viewModel =
            ViewModelProvider(requireActivity()).get(SearchMovieViewModel::class.java)

        viewModel.queryName(queryName)

        movieAdapter = activity?.let { MovieAdapter(itemClick, it) }!!

        recyclerView.apply {
            recyclerView.layoutManager = GridLayoutManager(activity, noOfColumns)

            //  recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = movieAdapter
        }



        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.searchMovies
                .onStart { MyProgressBar.showProgress(requireActivity()) }
                .catch { MyProgressBar.hideProgress() }
                .onCompletion { MyProgressBar.hideProgress() }
                .collectLatest { data ->
                    MyProgressBar.hideProgress()
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


    override fun onMovieClick(view: View?, position: Int, movieData: Results) {
        if (view != null && checkConnection(requireActivity())) {

            val bundle = Bundle()
            val movieID = movieData.id.toString()

            bundle.putString("movieID", movieID)

            view.findNavController()
                .navigate(R.id.action_searchMovieFragment_to_singleMovieDetailFragment, bundle)

        }

    }

    override fun onClick(view: View) {
        view.findNavController().popBackStack()
    }


}