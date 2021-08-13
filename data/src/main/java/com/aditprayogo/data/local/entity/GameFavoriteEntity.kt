package com.aditprayogo.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Aditiya Prayogo.
 */

@Entity(tableName = "game_favorite_entity")
data class GameFavoriteEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id") val id: Int,

    @ColumnInfo(name = "backgroundImage") val backgroundImage: String?,

    @ColumnInfo(name = "name") val name: String?,

    @ColumnInfo(name = "playtime") val playtime: Int?,

    @ColumnInfo(name = "rating") val rating: Double?,

    @ColumnInfo(name = "ratingsCount") val ratingsCount: Int?,

    @ColumnInfo(name = "released") val released: String?,

    @ColumnInfo(name = "information") val information: String?,

    @ColumnInfo(name = "genres") val genres: String?
)