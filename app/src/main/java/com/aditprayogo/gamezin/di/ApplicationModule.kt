package com.aditprayogo.gamezin.di

import com.aditprayogo.core.domain.usecases.GameUseCase
import com.aditprayogo.core.domain.usecases.GameUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Aditiya Prayogo.
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class ApplicationModule {

    @Binds
    @ViewModelScoped
    abstract fun provideGameUseCase(gameUseCaseImpl: GameUseCaseImpl) : GameUseCase

}