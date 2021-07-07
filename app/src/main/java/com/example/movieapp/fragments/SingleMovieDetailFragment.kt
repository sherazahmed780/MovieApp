package com.example.movieapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.movieapp.databinding.FragmentSingleMovieDetailBinding
import com.example.movieapp.listeners.BackBtnListener
import com.example.movieapp.viewModels.SingleMovieViewModel


class SingleMovieDetailFragment : Fragment(), BackBtnListener {
    private lateinit var binding: FragmentSingleMovieDetailBinding

    lateinit var viewModel: SingleMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding =
            FragmentSingleMovieDetailBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        binding.clickHandler = this

        val value: String = arguments?.getString("movieID")!!

        viewModel =
            ViewModelProvider(requireActivity()).get(SingleMovieViewModel::class.java)


        viewModel.getSingleMovie(value)

        viewModel.getSingleData.observe(viewLifecycleOwner, { response ->


            binding.singleMovie = response


        })


        return view
    }

    override fun onClick(view: View) {

        view.findNavController().popBackStack()

    }


}
