package com.aditprayogo.gamezin.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aditprayogo.core.domain.entity.GameFavoriteData
import com.aditprayogo.core.domain.usecases.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
interface FavoriteViewModelContract {
    fun getAllFavoriteGames(): LiveData<PagingData<GameFavoriteData>>
}

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val gameUseCase: GameUseCase
) : ViewModel(),FavoriteViewModelContract  {

    override fun getAllFavoriteGames()  = gameUseCase.getAllGamesFromDb().asLiveData().cachedIn(viewModelScope)
}