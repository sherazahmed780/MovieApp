package com.example.movieapp.paging

import androidx.paging.PagingSource
import com.example.movieapp.network.ApiService
import com.example.movieapp.model.homeMovies.Results
import com.example.movieapp.utils.BaseUrls

class MoviePagingSource(
    private val apiService: ApiService,
) : PagingSource<Int, Results>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        val nextPage = params.key ?: 1
        return try {


            val response = apiService.getMovies(BaseUrls.API_KEY, nextPage)

            LoadResult.Page

            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = response.page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}