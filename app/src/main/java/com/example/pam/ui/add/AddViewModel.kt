package com.example.pam.ui.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pam.data.AdminRepository
import com.example.pam.ui.AddEvent
import com.example.pam.ui.AddUIState
import com.example.pam.ui.toAdmin

class AddViewModel(private val adminRepository: AdminRepository) : ViewModel() {

    var addUIState by mutableStateOf(AddUIState())
        private set

    fun updateAddUIState(addEvent: AddEvent) {
        addUIState = AddUIState(addEvent = addEvent)
    }

    suspend fun addAdmin() {
        adminRepository.save(addUIState.addEvent.toAdmin())
    }
}