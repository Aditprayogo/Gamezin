package com.aditprayogo.gamezin.game_detail

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.aditprayogo.core.domain.entity.GameDataEntity
import com.aditprayogo.core.utils.load
import com.aditprayogo.gamezin.R
import com.aditprayogo.gamezin.databinding.ActivityGameDetailBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailActivity : AppCompatActivity() {

    private val binding: ActivityGameDetailBinding by lazy {
        ActivityGameDetailBinding.inflate(layoutInflater)
    }

    private val gameDetailViewModel by viewModels<GameDetailViewModel>()

    private var gameDataEntity: GameDataEntity? = null
    private var gameId: String? = null

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
                chipGroup.apply {
                    for (genre in data.genres?.indices!!) {
                        val chip = Chip(this@GameDetailActivity)
                        chip.text = data.genres!![genre]
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            chip.chipBackgroundColor = getColorStateList(R.color.colorGreen)
                        }
                        chip.setTextColor(resources.getColor(R.color.white))
                        addView(chip)
                    }
                }

            }

        }
    }

    private fun initToolbar() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val GAME_ID = "game_id"
    }
}