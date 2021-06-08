package com.aditprayogo.data.remote.network

import com.aditprayogo.data.remote.responses.GamesResponse
import retrofit2.http.GET

/**
 * Created by Aditiya Prayogo.
 */
interface GameSerivce {

    /**
     * get all games
     */
    @GET("games")
    suspend fun getAllGames() : GamesResponse

}