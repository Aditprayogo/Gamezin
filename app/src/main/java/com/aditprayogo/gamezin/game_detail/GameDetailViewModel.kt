package com.aditprayogo.gamezin.game_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditprayogo.core.domain.entity.GameData
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
interface GameDetailViewModelContract {
    fun getDetailGame(gameId: String)
}

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val gameUseCase: GameUseCase
) : ViewModel(), GameDetailViewModelContract {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _networkError = MutableLiveData<Boolean>()
    val networkError: LiveData<Boolean> get() = _networkError

    private val _resultDetailGameFromApi = MutableLiveData<GameData>()
    val resultDetailGameFromApi: LiveData<GameData> get() = _resultDetailGameFromApi

    override fun getDetailGame(gameId: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            gameUseCase.getDetailGame(gameId).collect {
                when (it) {
                    is ResultState.Success -> {
                        _state.value = LoaderState.HideLoading
                        _resultDetailGameFromApi.postValue(it.data!!)
                    }
                    is ResultState.Error -> {
                        _error.postValue(it.error)
                    }
                    is ResultState.NetworkError -> {
                        _networkError.postValue(true)
                    }
                }
            }
        }
    }
}