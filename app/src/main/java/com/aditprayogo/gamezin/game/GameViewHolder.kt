package com.aditprayogo.gamezin.game

import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.core.domain.entity.GameDataEntity
import com.aditprayogo.core.utils.load
import com.aditprayogo.gamezin.R
import com.aditprayogo.gamezin.databinding.ItemRowGameBinding
import com.google.android.material.chip.Chip

/**
 * Created by Aditiya Prayogo.
 */
class GameViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemRowGameBinding.bind(itemView)

    fun bind(data : GameDataEntity) {
        with(binding) {
            data.backgroundImage?.let { binding.imgGame.load(it) }
            txtName.text = data.name
            txtGenre.text = data.genres?.joinToString(
                prefix = "",
                separator = ", ",
                postfix = ""
            )
            txtRatting.text = data.rating.toString()
        }
    }
}