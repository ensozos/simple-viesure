package com.zosimadis.list

import com.zosimadis.model.Book

sealed interface ListUiState {

    data object Loading : ListUiState

    data class Error(
        val fallbackData: List<Book>? = emptyList(),
        val message: String = "Error",
    ) : ListUiState

    data class Success(
        val books: List<Book>,
    ) : ListUiState
}
