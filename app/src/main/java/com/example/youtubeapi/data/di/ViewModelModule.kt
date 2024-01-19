package com.example.youtubeapi.data.di

import com.example.youtubeapi.ui.playlistitems.PlayListItemsViewModel
import com.example.youtubeapi.ui.playlists.PlayListsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel {
        PlayListsViewModel(get())
    }
    viewModel {
        PlayListItemsViewModel(get())
    }
}