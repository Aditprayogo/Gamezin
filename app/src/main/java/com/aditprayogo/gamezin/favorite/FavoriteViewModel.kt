package com.aditprayogo.gamezin.favorite

import androidx.lifecycle.*
import com.aditprayogo.core.domain.entity.GameData
import com.aditprayogo.core.domain.entity.GameFavoriteData
import com.aditprayogo.core.domain.usecases.GameUseCase
import com.aditprayogo.core.utils.LoaderState
import com.aditprayogo.core.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
interface FavoriteViewModelContract {
    fun getAllFavoriteGames(): LiveData<List<GameFavoriteData>>
}

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val gameUseCase: GameUseCase
) : ViewModel(),FavoriteViewModelContract  {

    private val _resultGameFavorite = MutableLiveData<List<GameFavoriteData>>()
    val resultGameFavorite: LiveData<List<GameFavoriteData>> get() = _resultGameFavorite

    override fun getAllFavoriteGames()  = gameUseCase.getAllGamesFromDb().asLiveData()

}