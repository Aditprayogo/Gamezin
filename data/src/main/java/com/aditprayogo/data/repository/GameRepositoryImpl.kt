package com.aditprayogo.data.repository

import androidx.paging.*
import com.aditprayogo.core.domain.entity.GameData
import com.aditprayogo.core.domain.entity.GameFavoriteData
import com.aditprayogo.core.domain.repository.GameRepository
import com.aditprayogo.core.utils.ResultState
import com.aditprayogo.data.local.sources.LocalDataSource
import com.aditprayogo.data.mapper.DataMapper
import com.aditprayogo.data.remote.sources.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
@ExperimentalCoroutinesApi
class GameRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : GameRepository {

    /**
     * Remote
     */
    override suspend fun getAllgames(): Flow<ResultState<List<GameData>>> {
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

    override suspend fun getDetailGame(gameId: Int): Flow<ResultState<GameData>> {
        return flow {
            try {
                val response = remoteDataSource.getDetailGames(gameId)
                val dataMaped = DataMapper.mapGameResponseToGameDataEntitiy(response)
                emit(ResultState.Success(dataMaped))
            } catch (e: Exception) {
                emit(ResultState.Error(e.toString(), 500))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun searchGames(search: String): Flow<ResultState<List<GameData>>> =
        flow {
            try {
                val response = remoteDataSource.searchGames(search)
                val gameResponses = response.gameResponses
                val dataMaped = gameResponses?.let { DataMapper.mapResponseToDomainEntitiy(it) }
                emit(ResultState.Success(dataMaped))
            } catch (e: Exception) {
                emit(ResultState.Error(e.toString(), 500))
            }
        }.flowOn(Dispatchers.IO)

    /**
     * local
     */
    override fun getAllGamesFromDb(): Flow<PagingData<GameFavoriteData>> =
        Pager(config = PagingConfig(pageSize = 10)) {
            localDataSource.getAllGamesFromDb()
        }.flow.mapLatest {
            it.map { gameFavoriteEntity ->
                DataMapper.mapGameEntityToDomainGameEntity(gameFavoriteEntity)
            }
        }

    override fun getGamesById(id: Int): Flow<List<GameFavoriteData>> =
        localDataSource.getGameById(id).map {
            DataMapper.mapGameRoomEntityToDomainGameEntity(it)
        }

    override suspend fun insertGameToDb(game: GameFavoriteData) {
        val dataMapped = DataMapper.mapGameDomainEntityToGameRoomEntity(game)
        return localDataSource.insertGameToDb(dataMapped)
    }


    override suspend fun deleteGameFromDb(game: GameFavoriteData) {
        val dataMapped = DataMapper.mapGameDomainEntityToGameRoomEntity(game)
        return localDataSource.deleteGameFromDb(dataMapped)
    }
}