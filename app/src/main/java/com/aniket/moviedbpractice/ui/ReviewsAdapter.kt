package com.aniket.moviedbpractice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aniket.moviedbpractice.R
import com.aniket.moviedbpractice.databinding.ItemReviewBinding
import com.aniket.moviedbpractice.responses.Review

class ReviewsAdapter(private val list: Array<Review>) : RecyclerView.Adapter<ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ReviewViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_review, parent, false
            )
        )

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.binding.review = list[position]
    }

    override fun getItemCount() = list.size
}

class ReviewViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root)