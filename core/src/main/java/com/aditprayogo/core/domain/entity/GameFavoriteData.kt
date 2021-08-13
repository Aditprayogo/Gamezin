package com.aditprayogo.core.domain.entity

/**
 * Created by Aditiya Prayogo.
 */
data class GameFavoriteData(
    val backgroundImage: String?,
    val id: Int,
    val name: String?,
    val genres: String?,
    val playtime: Int?,
    val rating: Double?,
    val ratingsCount: Int?,
    val released: String?,
    val information: String?
)