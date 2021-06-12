package com.aditprayogo.data.remote.sources

import com.aditprayogo.data.remote.network.GameSerivce
import com.aditprayogo.data.remote.responses.GameResponse
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class RemoteDataSourceImpl @Inject constructor(
    private val gameSerivce: GameSerivce
) : RemoteDataSource {
    override suspend fun getGames() = gameSerivce.getAllGames()

    override suspend fun getDetailGames(gameId: String): GameResponse {
        return gameSerivce.getDetailGameById(gameId)
    }
}