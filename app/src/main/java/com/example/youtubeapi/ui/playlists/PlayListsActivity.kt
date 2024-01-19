package com.example.youtubeapi.ui.playlists

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.data.model.BaseResponse
import com.example.youtubeapi.data.service.Resource
import com.example.youtubeapi.databinding.ActivityPlaylistsBinding
import com.example.youtubeapi.ui.base.BaseActivity
import com.example.youtubeapi.ui.playlistitems.PlayListItemsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayListsActivity : BaseActivity() {
   // private val binding by viewBinding(ActivityPlaylistsBinding::bind, R.id.playlist_container)
    private lateinit var binding: ActivityPlaylistsBinding
    private val viewModel: PlayListsViewModel by viewModel()
    private val playListAdapter by lazy { PlaylistsAdapter(this::onClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaylistsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getPlaylists().stateHandler(
            success = {
                playListAdapter.submitList(it)
            },
            state = { state ->
                binding.progressBar.isVisible = state is Resource.Loading
            }
        )
        setupRecycler()
    }
    private fun setupRecycler()= with(binding.recyclerView){
        adapter = playListAdapter
        layoutManager = LinearLayoutManager(
            this@PlayListsActivity,
            LinearLayoutManager.VERTICAL,true
        )
    }

    private fun onClick(item: BaseResponse.Item) {
        val intent = Intent(this, PlayListItemsActivity::class.java)
        intent.putExtra("id", item.id)
        intent.putExtra("count", item.contentDetails.itemCount)
        startActivity(intent)
    }
}