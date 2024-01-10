package com.example.pam.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam.data.MakananRepository
import com.example.pam.ui.DetailUIState
import com.example.pam.ui.toDetailAdmin
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: MakananRepository
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val makananId: String = checkNotNull(savedStateHandle[DetailDestinationScreen.MakananId])

    val uiState: StateFlow<DetailUIState> =
        repository.getMakananById(makananId)
            .filterNotNull()
            .map {
                DetailUIState(addEvent = it.toDetailAdmin())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState()
            )

    suspend fun deleteadmin() {
        repository.delete(makananId)
    }
}