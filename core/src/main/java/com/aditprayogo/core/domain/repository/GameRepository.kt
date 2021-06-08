package com.aditprayogo.core.domain.repository

import com.aditprayogo.core.domain.entity.GameDataEntity
import com.aditprayogo.core.utils.ResultState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
interface GameRepository {
    suspend fun getAllgames() : Flow<ResultState<List<GameDataEntity>>>
}