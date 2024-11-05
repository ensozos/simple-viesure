package com.zosimadis.testing.data

import com.zosimadis.network.ViesureDataSource
import com.zosimadis.network.model.NetworkBookResource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.io.IOException
import java.util.concurrent.TimeoutException

class FakeNetworkDataSource(var failAttempts: Int = 0, var simulateTimeout: Boolean = false) :
    ViesureDataSource {
    private val networkResources = MutableSharedFlow<List<NetworkBookResource>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
    )
    private var attempts = 0

    override suspend fun getBooks(): List<NetworkBookResource> {
        if (simulateTimeout) {
            throw TimeoutException("Network request timed out")
        }

        if (attempts < failAttempts) {
            attempts++
            throw IOException()
        }
        return networkResources.replayCache.first()
    }

    fun fetchBooks(books: List<NetworkBookResource>) = networkResources.tryEmit(books)
}
