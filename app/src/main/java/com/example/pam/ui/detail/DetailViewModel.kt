package com.example.pam.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam.data.AdminRepository
import com.example.pam.ui.DetailUIState
import com.example.pam.ui.toDetailAdmin
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: AdminRepository
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val adminId: String = checkNotNull(savedStateHandle[DetailDestination.AdminId])

    val uiState: StateFlow<DetailUIState> =
        repository.getAdminById(adminId)
            .filterNotNull()
            .map {
                DetailUIState(addEvent = it.toDetailAdmin())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState()
            )

    suspend fun deleteadmin() {
        repository.delete(adminId)
    }
}