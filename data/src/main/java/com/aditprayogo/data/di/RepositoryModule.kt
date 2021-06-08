package com.aditprayogo.data.di

import com.aditprayogo.core.domain.repository.GameRepository
import com.aditprayogo.data.repository.GameRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Aditiya Prayogo.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideGameRepository(gameRepositoryImpl: GameRepositoryImpl) : GameRepository
}