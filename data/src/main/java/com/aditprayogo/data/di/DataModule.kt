package com.aditprayogo.data.di

import com.aditprayogo.data.remote.network.GameSerivce
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
    fun provideGameService(): GameSerivce =
        RetrofitConfig.retrofitClient().create(GameSerivce::class.java)

}