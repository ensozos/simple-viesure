package com.zosimadis.data.repository

import android.util.Log
import com.zosimadis.data.BooksRepository
import com.zosimadis.data.model.asEntity
import com.zosimadis.database.dao.BookDao
import com.zosimadis.database.model.toBookModel
import com.zosimadis.model.Book
import com.zosimadis.network.Dispatcher
import com.zosimadis.network.ViesureDataSource
import com.zosimadis.network.ViesureDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.io.IOException
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

internal class OfflineBooksRepository @Inject constructor(
    private val bookDao: BookDao,
    private val networkDataSource: ViesureDataSource,
    @Dispatcher(ViesureDispatchers.IO) private val dispatcher: CoroutineDispatcher,
) : BooksRepository {

    override suspend fun getLocalBooks(): List<Book> = withContext(dispatcher) {
        bookDao.getAllBooks().map { it.toBookModel() }
    }

    override fun getBookById(id: Int): Flow<Book> =
        bookDao.getBookById(id).map { it.toBookModel() }.flowOn(dispatcher)

    override fun getBooks(timesToRetry: Int): Flow<List<Book>> = flow {
        emit(getLocalBooks())

        try {
            val networkBooks = retryIO(attempts = timesToRetry) {
                withTimeout(3.seconds) {
                    networkDataSource.getBooks()
                }
            }
            bookDao.insertBooks(networkBooks.map { it.asEntity() })
        } catch (e: Exception) {
            Log.e("OfflineBooksRepository", "getBooks: $e")
        } finally {
            emit(getLocalBooks())
        }
    }.flowOn(dispatcher)
}

private suspend fun <T> retryIO(attempts: Int, block: suspend () -> T): T {
    repeat(attempts - 1) { attempt ->
        try {
            return block()
        } catch (e: IOException) {
            delay(2000L * (attempt + 1))
        }
    }
    return block()
}
