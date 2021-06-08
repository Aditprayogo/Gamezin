package com.aditprayogo.data.remote.sources

import com.aditprayogo.data.remote.responses.GamesResponse

/**
 * Created by Aditiya Prayogo.
 */
interface RemoteDataSource {
    suspend fun getGames() : GamesResponse
}