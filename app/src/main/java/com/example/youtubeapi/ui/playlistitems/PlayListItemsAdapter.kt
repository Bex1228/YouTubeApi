package com.example.youtubeapi.ui.playlistitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeapi.data.model.PlayListItemsModel
import com.example.youtubeapi.databinding.ItemDetailBinding

class PlayListItemsAdapter : ListAdapter<PlayListItemsModel.Item, PlaylistItemsViewHolder>(
    PlaylistItemsDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemsViewHolder {
        return PlaylistItemsViewHolder(
            ItemDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistItemsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PlaylistItemsViewHolder(private val binding: ItemDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PlayListItemsModel.Item?) = with(binding) {
        tvTitle.text = item?.snippet?.title
        Glide.with(image).load(item?.snippet?.thumbnails?.default?.url).into(image)
    }
}

class PlaylistItemsDiffCallback : DiffUtil.ItemCallback<PlayListItemsModel.Item>() {
    override fun areItemsTheSame(oldItem: PlayListItemsModel.Item, newItem: PlayListItemsModel.Item) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PlayListItemsModel.Item, newItem: PlayListItemsModel.Item) = oldItem == newItem

}