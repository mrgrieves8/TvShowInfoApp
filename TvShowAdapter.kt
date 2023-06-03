package com.example.tvshowsinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshowsinfo.databinding.ItemTvShowBinding
import com.example.tvshowsinfo.domain.TvShow

class TvShowAdapter(private val onTvShowClick: (TvShow) -> Unit) :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    var tvShows: List<TvShow> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemTvShowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = tvShows[position]
        holder.binding.tvShowName.text = tvShow.name
        holder.binding.tvShowGenre.text = tvShow.genre
        holder.binding.tvShowYear.text = tvShow.year.toString()

        Glide.with(holder.itemView)
            .load(tvShow.imageUrl)
            .centerCrop()
            .into(holder.binding.tvShowImage)

        holder.itemView.setOnClickListener {
            onTvShowClick(tvShow)
        }
    }


    override fun getItemCount(): Int = tvShows.size

    inner class TvShowViewHolder(val binding: ItemTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                onTvShowClick(tvShows[position])
            }
        }

        fun bind(tvShow: TvShow) {
            binding.tvShowName.text = tvShow.name
            binding.tvShowGenre.text = tvShow.genre
            Glide.with(itemView)
                .load(tvShow.imageUrl)
                .into(binding.tvShowImage)
        }
    }

}
