package com.aditprayogo.data.remote.responses


import com.google.gson.annotations.SerializedName

data class PlatformX(
    @SerializedName("platform")
    val platform: PlatformXX?,
    @SerializedName("released_at")
    val releasedAt: String?,
    @SerializedName("requirements_en")
    val requirementsEn: Any?,
    @SerializedName("requirements_ru")
    val requirementsRu: Any?
)