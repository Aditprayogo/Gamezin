package com.aditprayogo.gamezin.favorite

import androidx.lifecycle.ViewModel
import com.aditprayogo.core.domain.usecases.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val gameUseCase: GameUseCase
) : ViewModel() {

}