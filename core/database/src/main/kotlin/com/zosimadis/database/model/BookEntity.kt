package com.zosimadis.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zosimadis.model.Book

@Entity(tableName = "book_table")
data class BookEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
    val author: String,
    val releaseDate: Long,
)

fun BookEntity.toBookModel() = Book(
    id = id,
    title = title,
    description = description,
    author = author,
    imageUrl = imageUrl,
    releaseDate = releaseDate,
)
