package com.aditprayogo.data.mapper

import com.aditprayogo.core.domain.entity.GameData
import com.aditprayogo.data.remote.responses.GameResponse

/**
 * Created by Aditiya Prayogo.
 */
object DataMapper {

    /**
     * Response -> Domain model
     */
    fun mapResponseToDomainEntitiy(gameResponse: List<GameResponse>): List<GameData> =
        gameResponse.map { data ->
            GameData(
                backgroundImage = data.backgroundImage,
                clip = data.clip.toString(),
                genres = data.genres?.map { it.name.toString() },
                id = data.id,
                name = data.name,
                playtime = data.playtime,
                rating = data.rating,
                ratingsCount = data.ratingsCount,
                released = data.released,
                information = data.descriptionRaw
            )
        }

    /**
     * Response -> Domain model
     */
    fun mapGameResponseToGameDataEntitiy(data: GameResponse): GameData =
        GameData(
            backgroundImage = data.backgroundImage,
            clip = data.clip.toString(),
            genres = data.genres?.map { it.name.toString() },
            id = data.id,
            name = data.name,
            playtime = data.playtime,
            rating = data.rating,
            ratingsCount = data.ratingsCount,
            released = data.released,
            information = data.descriptionRaw
        )
}