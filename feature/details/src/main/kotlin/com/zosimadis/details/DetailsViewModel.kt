package com.zosimadis.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.zosimadis.data.Result
import com.zosimadis.details.navigation.DetailsRoute
import com.zosimadis.domain.usecase.GetBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getBookUseCase: GetBookUseCase,
) : ViewModel() {

    private val bookId = savedStateHandle.toRoute<DetailsRoute>().id

    val detailsUiState: StateFlow<DetailsUiState> = getBookUseCase(bookId)
        .map {
            when (it) {
                is Result.Success -> DetailsUiState.Success(
                    title = it.data.title,
                    description = it.data.description,
                    author = it.data.author,
                    imageUrl = it.data.imageUrl,
                    releaseDate = formatDate(it.data.releaseDate),
                )

                is Result.Error -> DetailsUiState.Error
                Result.Loading -> DetailsUiState.Loading
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DetailsUiState.Loading,
        )

    private fun formatDate(releaseDate: Long): String {
        val date = Date(releaseDate)
        val formatter = SimpleDateFormat("E, MMM d, ''yy", Locale.getDefault())
        return formatter.format(date)
    }
}
