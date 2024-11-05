package com.zosimadis.data.repository

import com.zosimadis.data.model.asEntity
import com.zosimadis.database.dao.BookDao
import com.zosimadis.database.model.BookEntity
import com.zosimadis.database.model.toBookModel
import com.zosimadis.network.model.NetworkBookResource
import com.zosimadis.testing.data.FakeBookDao
import com.zosimadis.testing.data.FakeNetworkDataSource
import com.zosimadis.testing.util.MainDispatcherRule
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds

class OfflineBooksRepositoryTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()
    private lateinit var networkDataSource: FakeNetworkDataSource
    private lateinit var bookDao: BookDao
    private lateinit var repository: OfflineBooksRepository

    @Before
    fun setUp() {
        bookDao = FakeBookDao()
        networkDataSource = FakeNetworkDataSource()
        repository = OfflineBooksRepository(
            bookDao = bookDao,
            networkDataSource = networkDataSource,
            dispatcher = dispatcherRule.testDispatcher,
        )
    }

    @Test
    fun `getBooks pulls from network then inserts into local database`() = runTest {
        networkDataSource.fetchBooks(networkBooks)

        val result = repository.getBooks(timesToRetry = 1).toList()

        assertEquals(2, result.size)
        assertTrue(result[0].isEmpty())
        assertEquals(networkBooks.map { it.asEntity().toBookModel() }, result[1])
        assertEquals(networkBooks.size, bookDao.getAllBooks().size)
    }

    @Test
    fun `getBooks returns local books first, then fetches and returns network books`() = runTest {
        networkDataSource.fetchBooks(networkBooks)
        bookDao.insertBooks(localBooks)

        val result = repository.getBooks(timesToRetry = 1).toList()

        assertEquals(2, result.size)
        assertEquals(localBooks.map { it.toBookModel() }, result[0])
        assertEquals(networkBooks.map { it.asEntity().toBookModel() }, result[1])
    }

    @Test
    fun `getBooks returns only local books when network fails after retries`() = runTest {
        bookDao.insertBooks(localBooks)
        networkDataSource.fetchBooks(networkBooks)
        networkDataSource.failAttempts = 4

        val result = repository.getBooks(timesToRetry = 1).toList()

        assertEquals(2, result.size)
        assertEquals(localBooks.map { it.toBookModel() }, result[0])
    }

    @Test
    fun `getBooks handles network timeout`() = runTest {
        bookDao.insertBooks(localBooks)
        networkDataSource.simulateTimeout = true

        val result = withTimeout(5.seconds) {
            repository.getBooks(timesToRetry = 2).toList()
        }

        assertEquals(2, result.size)
        assertEquals(localBooks.map { it.toBookModel() }, result[0])
    }

    @Test
    fun `getBookById returns correct book from local database`() = runTest {
        bookDao.insertBooks(localBooks)
        val targetBook = localBooks[1]

        val result = repository.getBookById(targetBook.id).first()

        assertEquals(targetBook.toBookModel(), result)
    }
}

private val localBooks = listOf(
    BookEntity(1, "Test1", "", "", "", 0),
    BookEntity(2, "Test2", "", "", "", 0),
    BookEntity(3, "Test3", "", "", "", 0),
    BookEntity(4, "Test4", "", "", "", 0),
)

private val networkBooks = listOf(
    NetworkBookResource(1, "Test1", "", "", "", "1/1/2022"),
    NetworkBookResource(2, "Test1", "", "", "", "1/1/2022"),
    NetworkBookResource(3, "Test1", "", "", "", "1/1/2022"),
)
