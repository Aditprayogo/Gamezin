package com.aditprayogo.data.local.sources

import com.aditprayogo.data.local.dao.GameDao
import com.aditprayogo.data.local.entity.GameFavoriteEntity
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class LocalDataSourceImpl @Inject constructor(private val gameDao: GameDao) : LocalDataSource {
    override fun getAllGamesFromDb() = gameDao.getAllGames()

    override fun getGameById(id: Int) = gameDao.getGameById(id)

    override suspend fun insertGameToDb(game: GameFavoriteEntity) = gameDao.insertGameToDb(game)

    override suspend fun deleteGameFromDb(game: GameFavoriteEntity) = gameDao.deleteGameFromDb(game)
}