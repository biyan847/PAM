package com.example.pam.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam.data.MakananRepository
import com.example.pam.ui.AddEvent
import com.example.pam.ui.AddUIState
import com.example.pam.ui.toAdmin
import com.example.pam.ui.toUIStateAdmin
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditMakananViewModel(
    savedStateHandle: SavedStateHandle,
    private val makananrepo : MakananRepository
): ViewModel() {
    var makanUiState by mutableStateOf(AddUIState())
        private set

    private  val makananId: String = checkNotNull(savedStateHandle[EditMakanan.makananId])

    init {
        viewModelScope.launch {
            makanUiState =
                makananrepo.getMakananById(makananId)
                    .filterNotNull()
                    .first()
                    .toUIStateAdmin()
        }
    }
    fun updateUiState(addEvent: AddEvent ){
        makanUiState = makanUiState.copy(addEvent = addEvent)
    }
    suspend fun updatemakanan(){
        makananrepo.update(makanUiState.addEvent.toAdmin())
    }
}