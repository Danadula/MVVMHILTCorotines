package com.mano.movieslistapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mano.movieslistapp.databinding.MoviesTemLayoutBinding
import com.mano.movieslistapp.model.TvShowItem

class MovieAdapter(private var context: Context) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: MoviesTemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<TvShowItem>() {
        override fun areItemsTheSame(oldItem: TvShowItem, newItem: TvShowItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvShowItem, newItem: TvShowItem): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var movieShowItem: List<TvShowItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context

        return MyViewHolder(
            MoviesTemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTvShow = movieShowItem[position]

        holder.binding.apply {
            textView.text = currentTvShow.name

            imageView.load(currentTvShow.image.original) {
                crossfade(true)
                crossfade(1000)
            }
        }

        holder.binding.cardView.setOnClickListener {
            Toast.makeText(context, "Movie name: ${currentTvShow.name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = movieShowItem.size
}