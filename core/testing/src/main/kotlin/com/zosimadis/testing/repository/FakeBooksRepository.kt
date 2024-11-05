package com.zosimadis.testing.repository

import com.zosimadis.data.BooksRepository
import com.zosimadis.model.Book
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class FakeBooksRepository : BooksRepository {

    private val booksFlow: MutableSharedFlow<List<Book>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    private val errorFlow: MutableSharedFlow<Throwable?> = MutableStateFlow(null)

    fun sendBooks(books: List<Book>) {
        booksFlow.tryEmit(books)
    }

    fun sendError(error: Throwable) {
        errorFlow.tryEmit(error)
    }

    override suspend fun getLocalBooks(): List<Book> = booksFlow.replayCache.first()

    override fun getBooks(timesToRetry: Int): Flow<List<Book>> = booksFlow
        .combine(errorFlow) { books, error ->
            if (error != null) throw error else books
        }

    override fun getBookById(id: Int): Flow<Book> = booksFlow
        .map { books ->
            books.find { it.id == id } ?: throw NoSuchElementException("Book not found")
        }
}
