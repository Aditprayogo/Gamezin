package com.aditprayogo.data.remote.sources

import com.aditprayogo.data.remote.network.GameSerivce
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class RemoteDataSourceImpl @Inject constructor(
    private val gameSerivce: GameSerivce
) : RemoteDataSource {
    override suspend fun getGames() = gameSerivce.getAllGames()
}