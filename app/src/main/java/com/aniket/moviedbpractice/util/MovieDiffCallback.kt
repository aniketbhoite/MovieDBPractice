package com.aniket.moviedbpractice.util

import androidx.recyclerview.widget.DiffUtil
import com.aniket.moviedbpractice.responses.MovieData

class MovieDiffCallback(
    private val oldList: List<MovieData>,
    private val newList: List<MovieData>
) : DiffUtil.Callback() {


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val movieId = oldList[oldItemPosition].id
        val movieIdNew = newList[newItemPosition].id

        return movieId == movieIdNew
    }


}