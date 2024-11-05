package com.zosimadis.testing.data

import com.zosimadis.database.dao.BookDao
import com.zosimadis.database.model.BookEntity
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class FakeBookDao : BookDao {
    private val entitiesFlow: MutableSharedFlow<List<BookEntity>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    override fun getBookById(id: Int): Flow<BookEntity> = entitiesFlow.map {
        it.find { it.id == id }!!
    }

    override fun getAllBooks(): List<BookEntity> = entitiesFlow.replayCache.firstOrNull() ?: emptyList()

    override fun insertBooks(books: List<BookEntity>) {
        entitiesFlow.tryEmit(books)
    }
}
