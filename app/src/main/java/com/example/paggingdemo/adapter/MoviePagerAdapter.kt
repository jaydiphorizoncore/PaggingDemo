package com.example.paggingdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paggingdemo.databinding.ItemMovieBinding
import com.example.paggingdemo.model.Result

class MoviePagerAdapter :
    PagingDataAdapter<Result, MoviePagerAdapter.ViewHolder>(MovieComparator) {
    class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        /* val imageView: ImageView = itemView.findViewById(R.id.imageView)
         val textView: TextView = itemView.findViewById(R.id.textView)*/
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)!!
        /* holder.textView.text = movie.title
         Glide.with(context).load("https://image.tmdb.org/t/p/w300" + movie.posterPath)
             .into(holder.imageView)*/
        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w300" + movie.posterPath)
            .into(holder.binding.imageView)

        holder.binding.textView.text = movie.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            //LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    object MovieComparator : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.originalTitle == newItem.originalTitle
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

}