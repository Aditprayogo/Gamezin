package com.aditprayogo.core.domain.entity

/**
 * Created by Aditiya Prayogo.
 */
data class GameData(
    val backgroundImage: String?,
    val clip: String?,
    val genres: List<String>?,
    val id: Int?,
    val name: String?,
    val playtime: Int?,
    val rating: Double?,
    val ratingsCount: Int?,
    val released: String?,
    val information : String?
)