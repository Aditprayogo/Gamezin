package com.aditprayogo.core.domain.usecases

import com.aditprayogo.core.domain.entity.GameDataEntity
import com.aditprayogo.core.utils.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface GameUseCase {
    suspend fun getAllGames() : Flow<ResultState<List<GameDataEntity>>>
}