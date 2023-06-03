package com.example.tvshowsinfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tvshowsinfo.domain.TvShow
import com.example.tvshowsinfo.repository.TvShowRepository
import kotlinx.coroutines.launch

class TvShowViewModel(private val repository: TvShowRepository) : ViewModel() {

    val allTvShows: LiveData<List<TvShow>> = repository.allShows.asLiveData()

    fun insert(tvShow: TvShow) = viewModelScope.launch {
        repository.insert(tvShow)
    }

    fun delete(tvShow: TvShow) = viewModelScope.launch {
        repository.delete(tvShow)
    }

    fun update(tvShow: TvShow) = viewModelScope.launch {
        repository.update(tvShow)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun getTvShow(id: Int): LiveData<TvShow> = repository.getTvShow(id).asLiveData()
}

class TvShowViewModelFactory(private val repository: TvShowRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TvShowViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TvShowViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
