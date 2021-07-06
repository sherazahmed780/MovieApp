package com.example.movieapp.adapters

import android.view.LayoutInflater

import android.view.ViewGroup

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.MovieItemBinding
import com.example.movieapp.listeners.MovieItemClickListener

import com.example.movieapp.model.homeMovies.Results


class MovieAdapter constructor(private val clickListener: MovieItemClickListener,
    private val activity: FragmentActivity,
) :
    PagingDataAdapter<Results, MovieAdapter.BindingViewHolder>(MovieComparator) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val rootView: ViewDataBinding =
            MovieItemBinding.inflate(LayoutInflater.from(activity), parent, false)
        return BindingViewHolder(rootView)

    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {

        val results: Results = getItem(position)!!

        holder.itemBinding.setVariable(BR.movieItem, results)
        holder.itemBinding.executePendingBindings()

        holder.itemView.setOnClickListener {

            clickListener.onMovieClick(it,position,results)

        }

    }


    class BindingViewHolder(val itemBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    object MovieComparator : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            // Id is unique.
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem == newItem
        }
    }

}