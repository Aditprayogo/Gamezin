package com.aditprayogo.gamezin.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditprayogo.core.domain.entity.GameDataEntity
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
interface SearchViewModelContract {
    fun searchGames(search: String)
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val gameUseCase: GameUseCase
) : ViewModel(), SearchViewModelContract {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState> get() = _state

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _networkError = MutableLiveData<Boolean>()
    val networkError: LiveData<Boolean> get() = _networkError

    private val _resultGameApi = MutableLiveData<List<GameDataEntity>>()
    val resultGameApi: LiveData<List<GameDataEntity>> get() = _resultGameApi

    override fun searchGames(search: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            gameUseCase.searchGames(search).collect {
                when (it) {
                    is ResultState.Success -> {
                        _resultGameApi.postValue(it.data!!)
                        _state.value = LoaderState.HideLoading
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