package com.zosimadis.domain.usecase

import com.zosimadis.data.BooksRepository
import com.zosimadis.data.Result
import com.zosimadis.data.asResult
import com.zosimadis.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val booksRepository: BooksRepository,
) {
    operator fun invoke(): Flow<Result<List<Book>>> =
        booksRepository.getBooks()
            .map { books -> books.sortedBy { it.releaseDate } }
            .asResult(onErrorFallback = { booksRepository.getLocalBooks() })
}
