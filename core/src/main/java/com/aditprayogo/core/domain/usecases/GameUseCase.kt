package com.aditprayogo.core.domain.usecases

import com.aditprayogo.core.domain.entity.GameData
import com.aditprayogo.core.utils.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface GameUseCase {
    suspend fun getAllGames() : Flow<ResultState<List<GameData>>>
    suspend fun getDetailGame(gameId : String) : Flow<ResultState<GameData>>
    suspend fun searchGames(search : String) : Flow<ResultState<List<GameData>>>
}