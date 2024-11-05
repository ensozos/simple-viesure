package com.zosimadis.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error<T>(val fallbackData: T? = null, val exception: Throwable) : Result<T>
    data object Loading : Result<Nothing>
}

fun <T> Flow<T>.asResult(onErrorFallback: suspend () -> T? = { null }): Flow<Result<T>> =
    map<T, Result<T>> { Result.Success(it) }
        .onStart { emit(Result.Loading) }
        .catch {
            val fallbackData = onErrorFallback()
            emit(Result.Error(fallbackData = fallbackData, exception = it))
        }
