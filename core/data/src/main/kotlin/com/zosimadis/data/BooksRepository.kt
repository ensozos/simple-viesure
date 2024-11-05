package com.zosimadis.data

import com.zosimadis.model.Book
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    suspend fun getLocalBooks(): List<Book>
    fun getBooks(timesToRetry: Int = 3): Flow<List<Book>>
    fun getBookById(id: Int): Flow<Book>
}
