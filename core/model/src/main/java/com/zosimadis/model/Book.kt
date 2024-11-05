package com.zosimadis.model

data class Book(
    val id: Int,
    val title: String,
    val description: String,
    val author: String,
    val imageUrl: String,
    val releaseDate: Long,
)
