package com.aditprayogo.gamezin.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.aditprayogo.core.domain.entity.GameFavoriteData
import com.aditprayogo.gamezin.R

/**
 * Created by Aditiya Prayogo.
 */
class FavoriteAdapter : PagingDataAdapter<GameFavoriteData, FavoriteViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GameFavoriteData>() {
            override fun areItemsTheSame(
                oldItem: GameFavoriteData,
                newItem: GameFavoriteData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: GameFavoriteData,
                newItem: GameFavoriteData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_game, parent, false)
        return FavoriteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val game = getItem(position)
        game?.let { holder.bind(it) }
    }
}