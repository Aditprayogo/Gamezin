package com.aditprayogo.gamezin.game_detail

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aditprayogo.core.domain.entity.GameData
import com.aditprayogo.core.domain.entity.GameFavoriteData
import com.aditprayogo.core.utils.LoaderState
import com.aditprayogo.core.utils.load
import com.aditprayogo.core.utils.setGone
import com.aditprayogo.core.utils.setVisible
import com.aditprayogo.data.mapper.DataMapper
import com.aditprayogo.gamezin.R
import com.aditprayogo.gamezin.databinding.ActivityGameDetailBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailActivity : AppCompatActivity() {

    private val binding: ActivityGameDetailBinding by lazy {
        ActivityGameDetailBinding.inflate(layoutInflater)
    }

    private val gameDetailViewModel by viewModels<GameDetailViewModel>()

    private var gameData: GameData? = null

    private var gameFavoriteData: GameFavoriteData? = null

    private var gameId: Int? = null

    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getIntentData()
        initData()
        initObservers()
        initToolbar()
    }

    private fun initData() {
        gameId?.let {
            gameDetailViewModel.getDetailGame(it)
            gameDetailViewModel.getDetailGameFromDb(it)
        }
    }

    private fun getIntentData() {
        gameId = intent.getIntExtra(GAME_ID, 0)
    }


    private fun initToolbar() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            floatingIcon.setOnClickListener {
                checkFavoriteToggle()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun checkFavoriteToggle() {
        if (isFavorite) {
            gameFavoriteData?.let { gameDetailViewModel.deleteGameFromDb(it) }
        } else {
            gameData?.let {
                val dataMapped = DataMapper.mapGameDataDomainToGameFavoriteDomain(it)
                gameDetailViewModel.insertGameToDb(dataMapped)
            }
        }
    }

    private fun initObservers() {
        with(gameDetailViewModel) {
            state.observe(this@GameDetailActivity) {
                handleLoadingState(it)
            }
            resultDetailGameFromApi.observe(this@GameDetailActivity) {
                handleResultDetailGameFromApi(it)
            }
            gameId?.let {
                getDetailGameFromDb(it).observe(this@GameDetailActivity) { data ->
                    handleResultDetailFromDb(data)
                }
            }
            resultInsertToDbStatus.observe(this@GameDetailActivity) { status ->
                if (status) {
                    gameId?.let { gameDetailViewModel.getDetailGameFromDb(it) }
                    toast(this@GameDetailActivity, "Game has been added")
                }
            }
            resultDeleteFromDbStatus.observe(this@GameDetailActivity) { status ->
                if (status) {
                    gameId?.let { gameDetailViewModel.getDetailGameFromDb(it) }
                    toast(this@GameDetailActivity, "Game has been deleted")
                }
            }


        }
    }

    private fun handleLoadingState(it: LoaderState) {
        if (it is LoaderState.ShowLoading) {
            binding.floatingIcon.setGone()
        } else {
            binding.floatingIcon.setVisible()
        }
    }

    private fun handleResultDetailFromDb(data: List<GameFavoriteData>) {
        if (data.isEmpty()) {
            isFavorite = false
            val icon = R.drawable.ic_baseline_favorite_border_24
            binding.floatingIcon.setImageResource(icon)
        } else {
            gameFavoriteData = data.first()
            isFavorite = true
            val icon = R.drawable.ic_baseline_favorite_24
            binding.floatingIcon.setImageResource(icon)
        }
    }


    private fun handleResultDetailGameFromApi(data: GameData) {
        this.gameData = data
        with(binding) {
            data.let {
                imgGame.load(it.backgroundImage)
                information.text = data.information
                collapsingToolbar.title = data.name
                chipGroup.apply {
                    for (genre in data.genres?.indices!!) {
                        addChip(context = this@GameDetailActivity, label = data.genres!![genre])
                    }
                }
            }

        }
    }

    fun ChipGroup.addChip(context: Context, label: String) {
        Chip(context).apply {
            text = label
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                chipBackgroundColor = getColorStateList(R.color.colorGreen)
            }
            setTextColor(resources.getColor(R.color.white))
            addView(this)
        }
    }

    fun toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val GAME_ID = "game_id"
    }
}