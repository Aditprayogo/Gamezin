package com.aditprayogo.gamezin.favorite

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.core.domain.entity.GameData
import com.aditprayogo.core.domain.entity.GameFavoriteData
import com.aditprayogo.core.utils.load
import com.aditprayogo.gamezin.databinding.ItemRowGameBinding
import com.aditprayogo.gamezin.game_detail.GameDetailActivity

/**
 * Created by Aditiya Prayogo.
 */
class FavoriteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemRowGameBinding.bind(itemView)

    fun bind(data: GameFavoriteData) {
        with(itemView) {
            with(binding) {
                data.backgroundImage?.let { binding.imgGame.load(it) }
                txtName.text = data.name
                txtGenre.text = data.genres
                txtRatting.text = data.rating.toString()
            }

            setOnClickListener {
                context.startActivity(Intent(context, GameDetailActivity::class.java).apply {
                    putExtra(GameDetailActivity.GAME_ID, data.id)
                })
            }
        }
    }

}