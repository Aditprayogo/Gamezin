package com.aditprayogo.gamezin.game

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
interface GameViewModelContract {
    fun getAllGames()
}

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameUseCase: GameUseCase
) : ViewModel(), GameViewModelContract {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _networkError = MutableLiveData<Boolean>()
    val networkError: LiveData<Boolean> get()  = _networkError

    private val _resultGameApi = MutableLiveData<List<GameData>>()
    val resultGameApi: LiveData<List<GameData>> get() = _resultGameApi

    init {
        getAllGames()
    }

    override fun getAllGames() {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            gameUseCase.getAllGames().collect { data ->
                when (data) {
                    is ResultState.Success -> {
                        _state.value = LoaderState.HideLoading
                        _resultGameApi.postValue(data.data!!)
                    }
                    is ResultState.Error -> _error.postValue(data.error)
                    is ResultState.NetworkError -> _networkError.postValue(true)
                }
            }
        }
    }

}