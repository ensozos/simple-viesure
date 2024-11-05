package com.zosimadis.list

import com.zosimadis.domain.usecase.GetBooksUseCase
import com.zosimadis.model.Book
import com.zosimadis.testing.repository.FakeBooksRepository
import com.zosimadis.testing.util.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeoutException

class ListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = FakeBooksRepository()
    private val getBooksUseCase = GetBooksUseCase(repository)
    private lateinit var viewModel: ListViewModel

    @Before
    fun setUp() {
        viewModel = ListViewModel(getBooksUseCase)
    }

    @Test
    fun `listState is loading when is initialized`() = runTest {
        assertEquals(ListUiState.Loading, viewModel.listUiState.value)
    }

    @Test
    fun `listState is showing books when fetched data`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher()) { viewModel.listUiState.collect() }
        repository.sendBooks(sampleBooks)

        assert(viewModel.listUiState.value is ListUiState.Success)
        assertEquals(ListUiState.Success(sampleBooks), viewModel.listUiState.value)
    }

    @Test
    fun `listState is showing error when there is an error while fetching`() = runTest {
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) { viewModel.listUiState.collect() }

        repository.sendBooks(sampleBooks)
        repository.sendError(TimeoutException("Test error"))

        assertTrue(viewModel.listUiState.value is ListUiState.Error)
        val errorState = viewModel.listUiState.value as ListUiState.Error
        assertEquals(sampleBooks, errorState.fallbackData)
    }
}

private val sampleBooks = listOf(
    Book(
        id = 1,
        title = "test 1",
        author = "author 1",
        description = "description 1",
        imageUrl = "imageUrl 1",
        releaseDate = 123L,
    ),
    Book(
        id = 2,
        title = "test 2",
        author = "author 2",
        imageUrl = "imageUrl 2",
        description = "description 2",
        releaseDate = 1234L,
    ),
)
