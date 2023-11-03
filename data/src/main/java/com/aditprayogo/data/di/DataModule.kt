package com.aditprayogo.data.di

import android.app.Application
import androidx.room.Room
import com.aditprayogo.core.utils.GAME_DB
import com.aditprayogo.data.local.dao.GameDao
import com.aditprayogo.data.local.db.GameDatabase
import com.aditprayogo.data.remote.network.GameService
import com.aditprayogo.data.remote.network.RetrofitConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Aditiya Prayogo.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideGameService(): GameService =
        RetrofitConfig.retrofitClient().create(GameService::class.java)

    @Provides
    @Singleton
    fun provideGameDatabase(app: Application): GameDatabase {
        return Room.databaseBuilder(
            app,
            GameDatabase::class.java,
            GAME_DB
        ).build()
    }

    @Provides
    @Singleton
    fun provideGameDao(gameDatabase: GameDatabase): GameDao = gameDatabase.gameDao()

}