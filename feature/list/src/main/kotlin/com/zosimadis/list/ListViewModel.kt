package com.zosimadis.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zosimadis.data.Result
import com.zosimadis.data.Result.Error
import com.zosimadis.data.Result.Success
import com.zosimadis.domain.usecase.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    getBooksUseCase: GetBooksUseCase,
) : ViewModel() {

    val listUiState: StateFlow<ListUiState> =
        getBooksUseCase()
            .map { result ->
                when (result) {
                    is Error -> ListUiState.Error(result.fallbackData)
                    is Success -> ListUiState.Success(result.data)
                    Result.Loading -> ListUiState.Loading
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ListUiState.Loading,
            )
}
