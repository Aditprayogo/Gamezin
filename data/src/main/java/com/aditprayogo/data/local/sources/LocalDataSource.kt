package com.aditprayogo.data.local.sources

import com.aditprayogo.data.local.entity.GameFavoriteEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface LocalDataSource {
    fun getAllGamesFromDb(): Flow<List<GameFavoriteEntity>>
    fun getGameById(id : Int): Flow<List<GameFavoriteEntity>>
    suspend fun insertGameToDb(game : GameFavoriteEntity)
    suspend fun deleteGameFromDb(game : GameFavoriteEntity)
}