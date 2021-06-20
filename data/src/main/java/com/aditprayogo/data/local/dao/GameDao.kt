package com.aditprayogo.data.local.dao

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
    fun getAllGames() : Flow<List<GameFavoriteEntity>>

    /**
     * SELECT game by id
     */
    @Query("SELECT * FROM game_favorite_entity WHERE id = :id")
    fun getGameById(id : Int) : Flow<GameFavoriteEntity>

    /**
     * Insert game to db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGameToDb(game : GameFavoriteEntity)

    /**
     * DELETE games
     */
    @Delete
    fun deleteGameFromDb(game : GameFavoriteEntity)




}