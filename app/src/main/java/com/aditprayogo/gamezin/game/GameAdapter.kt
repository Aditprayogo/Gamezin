package com.aditprayogo.gamezin.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.core.domain.entity.GameData
import com.aditprayogo.gamezin.R

/**
 * Created by Aditiya Prayogo.
 */
class GameAdapter : RecyclerView.Adapter<GameViewHolder>() {

    private var games = mutableListOf<GameData>()

    fun setData(data : MutableList<GameData>) {
        this.games = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int = games.size
}