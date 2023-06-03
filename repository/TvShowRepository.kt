package com.example.tvshowsinfo.repository

import com.example.tvshowsinfo.data.TvShowDao
import com.example.tvshowsinfo.domain.TvShow
import kotlinx.coroutines.flow.Flow

class TvShowRepository(private val tvShowDao: TvShowDao) {

    val allShows: Flow<List<TvShow>> = tvShowDao.getTvShows()

    suspend fun insert(tvShow: TvShow) {
        tvShowDao.insert(tvShow)
    }

    suspend fun delete(tvShow: TvShow) {
        tvShowDao.deleteTvShow(tvShow.id)
    }

    suspend fun update(tvShow: TvShow) {
        tvShowDao.insert(tvShow)
    }

    suspend fun deleteAll() {
        tvShowDao.deleteAll()
    }

    fun getTvShow(id: Int): Flow<TvShow> {
        return tvShowDao.getTvShow(id)
    }
}
