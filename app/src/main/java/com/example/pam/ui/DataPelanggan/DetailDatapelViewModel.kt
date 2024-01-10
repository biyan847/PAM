package com.example.pam.ui.DataPelanggan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pam.data.PelangganRepository
import com.example.pam.ui.AddPelanggan
import com.example.pam.ui.AddUIState
import com.example.pam.ui.toPelanggan

class DetailDatapelViewModel(private val pelangganRepository: PelangganRepository) : ViewModel() {
    var AddUIState by mutableStateOf(AddUIState())
        private set

    fun UpdateAddUIState(addPelanggan: AddPelanggan) {
        AddUIState = AddUIState(addPelanggan = addPelanggan)
    }

    suspend fun addpelanggan() {
        pelangganRepository.save(AddUIState.addPelanggan.toPelanggan())
    }

}