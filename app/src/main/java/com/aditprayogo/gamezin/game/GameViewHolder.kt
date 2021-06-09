package com.aditprayogo.gamezin.game

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.core.domain.entity.GameDataEntity
import com.aditprayogo.core.utils.load
import com.aditprayogo.gamezin.databinding.ItemRowGameBinding

/**
 * Created by Aditiya Prayogo.
 */
class GameViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemRowGameBinding.bind(itemView)

    fun bind(data : GameDataEntity) {
        data.backgroundImage?.let { binding.imgGame.load(it) }
    }
}