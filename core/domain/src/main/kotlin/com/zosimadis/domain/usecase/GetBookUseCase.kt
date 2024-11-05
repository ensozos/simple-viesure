package com.zosimadis.domain.usecase

import com.zosimadis.data.BooksRepository
import com.zosimadis.data.Result
import com.zosimadis.data.asResult
import com.zosimadis.model.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookUseCase @Inject constructor(
    private val booksRepository: BooksRepository,
) {
    operator fun invoke(id: Int): Flow<Result<Book>> = booksRepository.getBookById(id = id).asResult()
}
