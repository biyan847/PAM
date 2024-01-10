package com.example.pam.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pam.MakananAplication
import com.example.pam.Model.Pelanggan
import com.example.pam.ui.DataPelanggan.DetailDatapelViewModel
import com.example.pam.ui.add.AddViewModel

fun CreationExtras.apkikasiAdmin(): MakananAplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MakananAplication)

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            AddViewModel(apkikasiAdmin().container.makananRepository)
        }
    }
    val comsumen = viewModelFactory {
        initializer {
            DetailDatapelViewModel(apkikasiAdmin().container.pelangganRepository)
        }
    }
}