package com.aditprayogo.core.domain.repository

import com.aditprayogo.core.domain.entity.GameData
import com.aditprayogo.core.utils.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface GameRepository {
    suspend fun getAllgames() : Flow<ResultState<List<GameData>>>
    suspend fun getDetailGame(gameId : String) : Flow<ResultState<GameData>>
    suspend fun searchGames(search : String) : Flow<ResultState<List<GameData>>>
}