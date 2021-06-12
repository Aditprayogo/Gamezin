package com.aditprayogo.gamezin.game_detail

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aditprayogo.core.domain.entity.GameDataEntity
import com.aditprayogo.core.utils.load
import com.aditprayogo.gamezin.databinding.ActivityGameDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailActivity : AppCompatActivity() {

    private val binding: ActivityGameDetailBinding by lazy {
        ActivityGameDetailBinding.inflate(layoutInflater)
    }

    private val gameDetailViewModel by viewModels<GameDetailViewModel>()

    private var gameDataEntity: GameDataEntity? = null
    private var gameId : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getIntentData()
        initObservers()
        initToolbar()
        initData()
    }

    private fun initData() {
        gameId?.let {
            gameDetailViewModel.getDetailGame(it)
        }
    }

    private fun getIntentData() {
        gameId = intent.getStringExtra(GAME_ID)
        Log.d("GAMEIDNYA", "getIntentData: $gameId")
    }

    private fun initObservers() {
        with(gameDetailViewModel) {
            resultDetailGameFromApi.observe(this@GameDetailActivity, {
                handleResultDetailGameFromApi(it)
            })
        }
    }

    private fun handleResultDetailGameFromApi(data: GameDataEntity?) {
        this.gameDataEntity = data
        with(binding) {
            data?.let {
                imgGame.load(it.backgroundImage)
                information.text = data.information
                collapsingToolbar.title = data.name
            }

        }
    }

    private fun initToolbar() {
        with(binding) {
            setSupportActionBar(toolbar)
        }
    }

    companion object {
        const val GAME_ID = "game_id"
    }
}