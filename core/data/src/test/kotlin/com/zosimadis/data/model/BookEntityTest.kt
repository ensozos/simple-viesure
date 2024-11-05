package com.zosimadis.data.model

import com.zosimadis.network.model.NetworkBookResource
import kotlin.test.Test

class BookEntityTest {

    @Test
    fun bookResource_asEntity_returnsBookEntity() {
        val bookResource = NetworkBookResource(
            id = 1,
            title = "test title",
            description = "test description",
            image = "test image",
            author = "test author",
            releaseDate = "1/1/2023",
        )
        val bookEntity = bookResource.asEntity()

        assert(bookEntity.id == bookResource.id)
        assert(bookEntity.title == bookResource.title)
        assert(bookEntity.description == bookResource.description)
        assert(bookEntity.imageUrl == bookResource.image)
        assert(bookEntity.author == bookResource.author)
        assert(bookEntity.releaseDate == 1672524000000L)
    }
}
