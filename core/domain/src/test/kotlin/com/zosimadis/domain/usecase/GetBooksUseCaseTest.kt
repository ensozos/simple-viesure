package com.zosimadis.domain.usecase

import com.zosimadis.data.Result
import com.zosimadis.model.Book
import com.zosimadis.testing.repository.FakeBooksRepository
import com.zosimadis.testing.util.MainDispatcherRule
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class GetBooksUseCaseTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val booksRepository = FakeBooksRepository()

    private val useCase = GetBooksUseCase(booksRepository)

    @Test
    fun whenCalled_booksAreReturnedSorted() = runTest {
        val emittedValues = mutableListOf<Result<List<Book>>>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            useCase().toList(emittedValues)
        }

        booksRepository.sendBooks(testBooks)

        assert(emittedValues.isNotEmpty())
        assert(emittedValues[0] is Result.Loading)

        val successResult = emittedValues[1] as? Result.Success<List<Book>>
        assert(successResult?.data != null)

        val sortedBooks = testBooks.sortedBy { it.releaseDate }
        assertEquals(sortedBooks, successResult?.data)
    }
}

private val testBooks = listOf(
    Book(
        id = 1,
        title = "Book 1",
        releaseDate = 1L,
        author = "Author 1",
        description = "test",
        imageUrl = "test",
    ),
    Book(
        id = 2,
        title = "Book 2",
        releaseDate = 2L,
        author = "Author 2",
        description = "test2",
        imageUrl = "test2",
    ),
)
