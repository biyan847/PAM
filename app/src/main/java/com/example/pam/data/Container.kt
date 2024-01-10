package com.example.pam.data

import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer {
    val makananRepository: MakananRepository
    val pelangganRepository : PelangganRepository
}

class MakananContainer : AppContainer{
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override val makananRepository: MakananRepository by lazy {
        MakananRepositoryImpl(firestore)
    }
    override val pelangganRepository: PelangganRepository by lazy {
        PelangganRepositoryImpl(firestore)
    }
}
