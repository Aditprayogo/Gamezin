package com.aditprayogo.data.repository

import com.aditprayogo.core.domain.repository.GameRepository
import com.aditprayogo.data.remote.sources.RemoteDataSource
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class GameRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : GameRepository {
}