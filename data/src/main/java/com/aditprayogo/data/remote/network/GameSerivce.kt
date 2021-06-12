package com.aditprayogo.data.remote.network

import com.aditprayogo.data.remote.responses.GameResponse
import com.aditprayogo.data.remote.responses.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Aditiya Prayogo.
 */
interface GameSerivce {

    /**
     * get all games
     */
    @GET("games")
    suspend fun getAllGames() : GamesResponse

    /**
     *  get detail games
     */
    @GET("games/{gameId}")
    suspend fun getDetailGameById(@Path("gameId") gameId : String) : GameResponse

}