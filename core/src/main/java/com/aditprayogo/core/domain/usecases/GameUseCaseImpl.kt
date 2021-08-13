package com.aditprayogo.core.domain.usecases

import androidx.paging.PagingData
import com.aditprayogo.core.domain.entity.GameData
import com.aditprayogo.core.domain.entity.GameFavoriteData
import com.aditprayogo.core.domain.repository.GameRepository
import com.aditprayogo.core.utils.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class GameUseCaseImpl @Inject constructor(
    private val gameRepository: GameRepository
) : GameUseCase {

    override suspend fun getAllGames(): Flow<ResultState<List<GameData>>> =
        gameRepository.getAllgames()

    override suspend fun getDetailGame(gameId: Int): Flow<ResultState<GameData>> =
        gameRepository.getDetailGame(gameId)

    override suspend fun searchGames(search: String): Flow<ResultState<List<GameData>>> =
        gameRepository.searchGames(search)

    override fun getAllGamesFromDb(): Flow<PagingData<GameFavoriteData>> =
        gameRepository.getAllGamesFromDb()

    override fun getGamesById(id: Int): Flow<List<GameFavoriteData>> =
        gameRepository.getGamesById(id)

    override suspend fun insertGameToDb(game: GameFavoriteData) =
        gameRepository.insertGameToDb(game)

    override suspend fun deleteGameFromDb(game: GameFavoriteData) =
        gameRepository.deleteGameFromDb(game)
}