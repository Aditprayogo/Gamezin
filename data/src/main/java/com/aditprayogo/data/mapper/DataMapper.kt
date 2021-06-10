package com.aditprayogo.data.mapper

import com.aditprayogo.core.domain.entity.GameDataEntity
import com.aditprayogo.data.remote.responses.GameResponse

/**
 * Created by Aditiya Prayogo.
 */
object DataMapper {

    fun mapResponseToDomainEntitiy(gameResponse : List<GameResponse>) : List<GameDataEntity> =
        gameResponse.map { data ->
            GameDataEntity(
                backgroundImage = data.backgroundImage,
                clip = data.clip.toString(),
                genres = data.genres?.map { it.name.toString() },
                id = data.id,
                name = data.name,
                playtime = data.playtime,
                rating = data.rating,
                ratingsCount = data.ratingsCount,
                released = data.released
            )
        }
}