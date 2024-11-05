package com.zosimadis.details

import androidx.lifecycle.SavedStateHandle
import com.zosimadis.domain.usecase.GetBookUseCase
import com.zosimadis.model.Book
import com.zosimadis.testing.repository.FakeBooksRepository
import com.zosimadis.testing.util.MainDispatcherRule
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
class DetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = FakeBooksRepository()
    private val getBookUseCase = GetBookUseCase(repository)
    private lateinit var viewmodel: DetailsViewModel

    @Before
    fun setUp() {
        viewmodel = DetailsViewModel(
            getBookUseCase = getBookUseCase,
            savedStateHandle = SavedStateHandle(
                mapOf("id" to books.first().id),
            ),
        )
    }

    @Test
    fun `detailsState is loading when initialized`() = runTest {
        assertEquals(DetailsUiState.Loading, viewmodel.detailsUiState.value)
    }

    @Test
    fun `detailState is success when data loaded`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewmodel.detailsUiState.collect() }
        repository.sendBooks(books)

        val expectedBook = books.first()
        val result = viewmodel.detailsUiState.value as DetailsUiState.Success

        assertEquals(result.title, expectedBook.title)
        assertEquals(result.imageUrl, expectedBook.imageUrl)
        assertEquals(result.author, expectedBook.author)
        assertEquals(result.description, expectedBook.description)
        assertEquals(result.releaseDate, "Wed, Jan 21, '70")
    }
}

private val books = listOf(
    Book(
        id = 1,
        title = "Book 1",
        releaseDate = 1727713889L,
        author = "Author 1",
        description = "test",
        imageUrl = "test",
    ),
)
