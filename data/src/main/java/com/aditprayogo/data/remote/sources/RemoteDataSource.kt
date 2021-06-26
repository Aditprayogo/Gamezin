package com.aditprayogo.data.remote.sources

import com.aditprayogo.data.remote.responses.GameResponse
import com.aditprayogo.data.remote.responses.GamesResponse

/**
 * Created by Aditiya Prayogo.
 */
interface RemoteDataSource {
    suspend fun getGames() : GamesResponse
    suspend fun getDetailGames(gameId : Int) : GameResponse
    suspend fun searchGames(search: String) : GamesResponse
}