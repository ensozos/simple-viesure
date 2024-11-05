package com.zosimadis.details

sealed interface DetailsUiState {
    data class Success(
        val imageUrl: String,
        val title: String,
        val description: String,
        val author: String,
        val releaseDate: String,
    ) : DetailsUiState

    data object Error : DetailsUiState
    data object Loading : DetailsUiState
}
