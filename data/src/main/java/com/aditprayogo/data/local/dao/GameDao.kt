package com.aditprayogo.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.aditprayogo.data.local.entity.GameFavoriteEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Aditiya Prayogo.
 */
@Dao
interface GameDao {

    /**
     * SELECT all favorite game
     */
    @Query("SELECT * FROM game_favorite_entity")
    fun getAllGames() : PagingSource<Int, GameFavoriteEntity>

    /**
     * SELECT game by id
     */
    @Query("SELECT * FROM game_favorite_entity WHERE id = :id")
    fun getGameById(id : Int) : Flow<List<GameFavoriteEntity>>

    /**
     * Insert game to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameToDb(game : GameFavoriteEntity)

    /**
     * DELETE games
     */
    @Delete
    suspend fun deleteGameFromDb(game : GameFavoriteEntity)




}