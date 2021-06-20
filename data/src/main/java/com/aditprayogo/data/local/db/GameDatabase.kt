package com.aditprayogo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aditprayogo.data.local.dao.GameDao
import com.aditprayogo.data.local.entity.GameFavoriteEntity

/**
 * Created by Aditiya Prayogo.
 */
@Database(
    entities = [GameFavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao() : GameDao
}