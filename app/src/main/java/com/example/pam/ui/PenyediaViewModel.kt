package com.example.pam.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pam.MakananAplication
import com.example.pam.Model.Pelanggan
import com.example.pam.ui.DataPelanggan.DetailDatapelViewModel
import com.example.pam.ui.add.AddViewModel
import com.example.pam.ui.detail.DetailViewModel
import com.example.pam.ui.edit.EditMakananViewModel
import com.example.pam.ui.menu.MenuViewModel

fun CreationExtras.apkikasiMakanan(): MakananAplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MakananAplication)

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            AddViewModel(apkikasiMakanan().container.makananRepository)
        }

        initializer {
            MenuViewModel(apkikasiMakanan().container.makananRepository)
        }

        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                apkikasiMakanan().container.makananRepository
            )
        }

        initializer {
            EditMakananViewModel(
                createSavedStateHandle(),
                apkikasiMakanan().container.makananRepository
            )
        }
    }
    val comsumen = viewModelFactory {
        initializer {
            DetailDatapelViewModel(apkikasiMakanan().container.pelangganRepository)
        }
    }

}