package com.aditprayogo.core.domain.usecases

import com.aditprayogo.core.domain.entity.GameDataEntity
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

    override suspend fun getAllGames(): Flow<ResultState<List<GameDataEntity>>> =
        gameRepository.getAllgames()

    override suspend fun getDetailGame(gameId: String): Flow<ResultState<GameDataEntity>> =
        gameRepository.getDetailGame(gameId)

    override suspend fun searchGames(search: String): Flow<ResultState<List<GameDataEntity>>> =
        gameRepository.searchGames(search)
}