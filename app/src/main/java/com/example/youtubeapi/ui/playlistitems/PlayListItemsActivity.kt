package com.example.youtubeapi.ui.playlistitems

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.youtubeapi.R
import com.example.youtubeapi.data.service.Resource
import com.example.youtubeapi.databinding.ActivityPlaylistItemsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayListItemsActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityPlaylistItemsBinding::bind, R.id.playlistitems_container)
    private val viewModel: PlayListItemsViewModel by viewModel()
    private val playlistItemsAdapter by lazy {
        PlayListItemsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvPlaylistItems.adapter = playlistItemsAdapter

        val getId = intent.getStringExtra("id").toString()
        val count: Int = intent.getIntExtra("count", 0)

        viewModel.getPlaylistItems(getId, count).observe(this) { result ->
            when (result) {
                is Resource.Error -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {}
                is Resource.Success -> {
                    playlistItemsAdapter.submitList(result.data)
                }
            }
        }
    }
}