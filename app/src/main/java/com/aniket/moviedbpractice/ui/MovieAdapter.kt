package com.aniket.moviedbpractice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aniket.moviedbpractice.R
import com.aniket.moviedbpractice.databinding.ItemMovieBinding
import com.aniket.moviedbpractice.databinding.ItemMovieThumbBinding
import com.aniket.moviedbpractice.responses.MovieData
import com.aniket.moviedbpractice.viewmodel.MovieItemViewModel
import com.aniket.moviedbpractice.viewmodel.MovieListViewModel
import java.util.*


class MovieAdapter(
    private val list: List<MovieData>,
    private val viewModel: MovieListViewModel?,
    private val itemType: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    companion object {
        const val MOVIE_FULL_ITEM_TYPE = 1001
        const val MOVIE_THUMB_ITEM_TYPE = 1002
    }

    private var listFiltered: MutableList<MovieData> = list.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == MOVIE_FULL_ITEM_TYPE)
            MovieVH(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_movie, parent, false
                )
            )
        else
            MovieThumbVH(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_movie_thumb, parent, false
                )
            )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is MovieVH) {
            viewModel?.let {
                holder.binding.viewModel = MovieItemViewModel(
                    listFiltered[position], viewModel
                )
            }

        } else if (holder is MovieThumbVH) {
            holder.binding.movieData = listFiltered[position]
        }
    }

    override fun getItemCount() = listFiltered.size

    override fun getItemViewType(position: Int): Int {
        return itemType
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString().toLowerCase(Locale.ENGLISH)
                listFiltered = if (query.isEmpty()) {
                    list.toMutableList()
                } else {
                    val tempList: MutableList<MovieData> = mutableListOf()
                    for (movieData in list) {
                        val title = movieData.title.toLowerCase(Locale.ENGLISH)
                        if (title.startsWith(query) || title.contains(" $query"))
                            tempList.add(movieData)
                    }
                    tempList
                }
                val filterResults = FilterResults()
                filterResults.values = listFiltered
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results != null) {
                    listFiltered = results.values as MutableList<MovieData>
                    notifyDataSetChanged()
                }
            }

        }
    }
}

class MovieVH(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

class MovieThumbVH(val binding: ItemMovieThumbBinding) : RecyclerView.ViewHolder(binding.root)