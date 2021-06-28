package com.aditprayogo.gamezin.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.core.domain.entity.GameFavoriteData
import com.aditprayogo.gamezin.R

/**
 * Created by Aditiya Prayogo.
 */
class FavoriteAdapter : RecyclerView.Adapter<FavoriteViewHolder>() {

    private var favoriteGames = mutableListOf<GameFavoriteData>()

    fun setFavoriteData(data: MutableList<GameFavoriteData>) {
        this.favoriteGames = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_game, parent, false)
        return FavoriteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoriteGames[position])
    }

    override fun getItemCount(): Int = favoriteGames.size
}