package com.example.tvshowsinfo.data

import androidx.room.*
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.tvshowsinfo.domain.TvShow

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tvshow_table")
    fun getTvShows(): Flow<List<TvShow>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TvShow)

    @Query("DELETE FROM tvshow_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM tvshow_table WHERE id = :id")
    fun getTvShow(id: Int): Flow<TvShow>

    @Query("DELETE FROM tvshow_table WHERE id = :id")
    suspend fun deleteTvShow(id: Int)
}