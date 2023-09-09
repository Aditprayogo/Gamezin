package com.aditprayogo.gamezin.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aditprayogo.core.domain.entity.GameData
import com.aditprayogo.core.domain.usecases.GameUseCase
import com.aditprayogo.core.utils.ResultState
import com.aditprayogo.gamezin.utils.CoroutineTestRule
import com.aditprayogo.gamezin.utils.DummyGame.gameDataList
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@Suppress("DEPRECATION")
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
internal class GameViewModelTest {

    private lateinit var gameViewModel: GameViewModel

    @Mock
    private lateinit var gameUseCase: GameUseCase

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var listResultGameApi: Observer<List<GameData>>

    @Captor
    lateinit var resultCaptor: ArgumentCaptor<List<GameData>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        gameViewModel = GameViewModel(gameUseCase)
        gameViewModel.resultGameApi.observeForever(listResultGameApi)
    }

    @Test
    fun `get game and return success`() = runTest {

        val result = flowOf(ResultState.Success(gameDataList))

        `when`(gameUseCase.getAllGames())
            .thenReturn(result)

        gameViewModel.getAllGames()

        verify(listResultGameApi, atLeastOnce()).onChanged(resultCaptor.capture())

        assertThat(listResultGameApi).isNotNull()

        assertThat(result.first().data).isEqualTo(resultCaptor.allValues.first())

        clearInvocations(gameUseCase, listResultGameApi)
    }
}