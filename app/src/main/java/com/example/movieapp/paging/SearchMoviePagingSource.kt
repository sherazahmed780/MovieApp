package com.example.movieapp.paging

import androidx.paging.PagingSource
import com.example.movieapp.model.homeMovies.Results
import com.example.movieapp.network.ApiService
import com.example.movieapp.utils.BaseUrls

class SearchMoviePagingSource(
    private val apiService: ApiService,private val queryName:String
) : PagingSource<Int, Results>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        val nextPage = params.key ?: 1


        return try {

            val response = apiService.getSearchedMovies(BaseUrls.API_KEY,queryName ,nextPage)

                LoadResult.Page(
                data = response.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = response.page + 1)

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}