package com.aditprayogo.data.repository

import com.aditprayogo.core.domain.entity.GameDataEntity
import com.aditprayogo.core.domain.repository.GameRepository
import com.aditprayogo.core.utils.ResultState
import com.aditprayogo.data.mapper.DataMapper
import com.aditprayogo.data.remote.sources.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class GameRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : GameRepository {

    override suspend fun getAllgames(): Flow<ResultState<List<GameDataEntity>>> {
        return flow {
            try {
                val response = remoteDataSource.getGames()
                val gameResponses = response.gameResponses
                val dataMaped = gameResponses?.let { DataMapper.mapResponseToDomainEntitiy(it) }
                emit(ResultState.Success(dataMaped))
            } catch (e: Exception) {
                emit(ResultState.Error(e.toString(), 500))
            }
        }.flowOn(Dispatchers.IO)
    }
}