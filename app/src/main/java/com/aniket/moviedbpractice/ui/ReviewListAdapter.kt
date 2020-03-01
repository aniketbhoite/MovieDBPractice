package com.aniket.moviedbpractice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aniket.moviedbpractice.R
import com.aniket.moviedbpractice.databinding.ItemReviewBinding
import com.aniket.moviedbpractice.responses.Review

class ReviewListAdapter :
    ListAdapter<Review, ReviewViewHolder>(ReviewCallBack()) {

    class ReviewCallBack : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ReviewViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_review, parent, false
        )
    )

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.binding.review = getItem(position)
    }
}

class ReviewViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root)