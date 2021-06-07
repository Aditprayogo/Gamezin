package com.aditprayogo.data.remote.responses


import com.google.gson.annotations.SerializedName

data class Filters(
    @SerializedName("years")
    val years: List<Year>?
)