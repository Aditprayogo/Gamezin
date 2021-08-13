package com.aditprayogo.data.di

import com.aditprayogo.data.local.sources.LocalDataSource
import com.aditprayogo.data.local.sources.LocalDataSourceImpl
import com.aditprayogo.data.remote.sources.RemoteDataSource
import com.aditprayogo.data.remote.sources.RemoteDataSourceImpl
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
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl) : RemoteDataSource

    @Binds
    @Singleton
    abstract fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImpl) : LocalDataSource

}