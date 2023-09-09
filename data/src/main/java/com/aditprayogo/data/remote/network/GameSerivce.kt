package com.aditprayogo.data.remote.network

import com.aditprayogo.data.remote.responses.GameResponse
import com.aditprayogo.data.remote.responses.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Aditiya Prayogo.
 */
interface GameService {

    /**
     * get all games
     */
    @GET("games")
    suspend fun getAllGames() : GamesResponse

    /**
     *  get detail games
     */
    @GET("games/{gameId}")
    suspend fun getDetailGameById(@Path("gameId") gameId : Int) : GameResponse

    /**
     * get games by name
     */
    @GET("games")
    suspend fun searchGames(@Query("search") search : String) : GamesResponse

}