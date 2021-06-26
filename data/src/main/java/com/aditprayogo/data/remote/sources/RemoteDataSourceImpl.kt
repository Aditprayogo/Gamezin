package com.aditprayogo.data.remote.sources

import com.aditprayogo.data.remote.network.GameSerivce
import com.aditprayogo.data.remote.responses.GameResponse
import com.aditprayogo.data.remote.responses.GamesResponse
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class RemoteDataSourceImpl @Inject constructor(
    private val gameSerivce: GameSerivce
) : RemoteDataSource {
    override suspend fun getGames() = gameSerivce.getAllGames()

    override suspend fun getDetailGames(gameId: Int): GameResponse =
        gameSerivce.getDetailGameById(gameId)

    override suspend fun searchGames(search: String): GamesResponse =
        gameSerivce.searchGames(search)
}