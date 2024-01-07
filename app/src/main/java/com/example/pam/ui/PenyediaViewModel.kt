package com.example.pam.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pam.AdminAplication
import com.example.pam.ui.add.AddViewModel

fun CreationExtras.apkikasiAdmin(): AdminAplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AdminAplication)

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            AddViewModel(apkikasiAdmin().container.adminRepository)
        }
    }
}