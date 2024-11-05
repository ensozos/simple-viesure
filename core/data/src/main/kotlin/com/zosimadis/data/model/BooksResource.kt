package com.zosimadis.data.model

import com.zosimadis.database.model.BookEntity
import com.zosimadis.network.model.NetworkBookResource
import java.text.SimpleDateFormat
import java.util.Locale

fun NetworkBookResource.asEntity() = BookEntity(
    id = id,
    title = title,
    description = description,
    imageUrl = image,
    author = author,
    releaseDate = SimpleDateFormat("d/M/yyyy", Locale.US).parse(releaseDate)?.time ?: 0L,
)
