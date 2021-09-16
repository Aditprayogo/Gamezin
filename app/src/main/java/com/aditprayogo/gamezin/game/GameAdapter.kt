package com.aditprayogo.gamezin.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aditprayogo.core.domain.entity.GameData
import com.aditprayogo.gamezin.databinding.ItemRowGameBinding

/**
 * Created by Aditiya Prayogo.
 */
class GameAdapter : ListAdapter<GameData, GameViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(
            ItemRowGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GameData>() {
            override fun areItemsTheSame(
                oldItem: GameData,
                newItem: GameData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: GameData,
                newItem: GameData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}