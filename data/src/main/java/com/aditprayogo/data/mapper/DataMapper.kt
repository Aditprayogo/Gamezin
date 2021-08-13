package com.aditprayogo.data.mapper

import com.aditprayogo.core.domain.entity.GameData
import com.aditprayogo.core.domain.entity.GameFavoriteData
import com.aditprayogo.data.local.entity.GameFavoriteEntity
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

    /**
     * map room entity (list) to domain entity (list)
     */
    fun mapGameEntityToDomainGameEntity(input: GameFavoriteEntity): GameFavoriteData =
        GameFavoriteData(
            backgroundImage = input.backgroundImage,
            id = input.id,
            name = input.name,
            playtime = input.playtime,
            rating = input.rating,
            ratingsCount = input.ratingsCount,
            released = input.released,
            information = input.information,
            genres = input.genres
        )

    /**
     * map room entity to domain entitiy
     */
    fun mapGameRoomEntityToDomainGameEntity(data: List<GameFavoriteEntity>): List<GameFavoriteData> =
        data.map { it ->
            GameFavoriteData(
                backgroundImage = it.backgroundImage,
                id = it.id,
                name = it.name,
                playtime = it.playtime,
                rating = it.rating,
                ratingsCount = it.ratingsCount,
                released = it.released,
                information = it.information,
                genres = it.genres
            )
        }

    /**
     * map room entity to domain entitiy
     */
    fun mapGameDomainEntityToGameRoomEntity(data: GameFavoriteData): GameFavoriteEntity =
        GameFavoriteEntity(
            backgroundImage = data.backgroundImage,
            id = data.id,
            name = data.name,
            playtime = data.playtime,
            rating = data.rating,
            ratingsCount = data.ratingsCount,
            released = data.released,
            information = data.information,
            genres = data.genres
        )

    /**
     * map Game Data -> to Game Favorite Data [Domain]
     */
    fun mapGameDataDomainToGameFavoriteDomain(data: GameData): GameFavoriteData =
        GameFavoriteData(
            backgroundImage = data.backgroundImage,
            id = data.id!!,
            name = data.name,
            playtime = data.playtime,
            rating = data.rating,
            ratingsCount = data.ratingsCount,
            released = data.released,
            information = data.information,
            genres = data.genres!!.joinToString(
                prefix = "",
                separator = ", ",
                postfix = ""
            )
        )
}