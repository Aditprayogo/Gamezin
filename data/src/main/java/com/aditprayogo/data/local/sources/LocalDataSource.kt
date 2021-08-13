package com.aditprayogo.data.local.sources

import androidx.paging.PagingSource
import com.aditprayogo.data.local.entity.GameFavoriteEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface LocalDataSource {
    fun getAllGamesFromDb(): PagingSource<Int, GameFavoriteEntity>
    fun getGameById(id : Int): Flow<List<GameFavoriteEntity>>
    suspend fun insertGameToDb(game : GameFavoriteEntity)
    suspend fun deleteGameFromDb(game : GameFavoriteEntity)
}