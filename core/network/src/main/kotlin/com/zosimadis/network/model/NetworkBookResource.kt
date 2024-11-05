package com.zosimadis.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkBookResource(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val author: String,
    @SerialName("release_date")
    val releaseDate: String,
)
