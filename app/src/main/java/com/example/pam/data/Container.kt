package com.example.pam.data

import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer {
    val adminRepository: AdminRepository
    val pelangganRepository : PelangganRepository

}

class AdminContainer : AppContainer{
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override val adminRepository: AdminRepository by lazy {
        AdminRepositoryImpl(firestore)
    }
    override val pelangganRepository: PelangganRepository by lazy {
        PelangganRepositoryImpl(firestore)
    }

}
