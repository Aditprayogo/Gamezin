package com.aditprayogo.core.domain.repository

import androidx.paging.PagingData
import com.aditprayogo.core.domain.entity.GameData
import com.aditprayogo.core.domain.entity.GameFavoriteData
import com.aditprayogo.core.utils.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface GameRepository {
    suspend fun getAllgames() : Flow<ResultState<List<GameData>>>
    suspend fun getDetailGame(gameId : Int) : Flow<ResultState<GameData>>
    suspend fun searchGames(search : String) : Flow<ResultState<List<GameData>>>

    fun getAllGamesFromDb() : Flow<PagingData<GameFavoriteData>>
    fun getGamesById(id : Int) : Flow<List<GameFavoriteData>>
    suspend fun insertGameToDb(game : GameFavoriteData)
    suspend fun deleteGameFromDb(game : GameFavoriteData)

}