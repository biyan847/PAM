package com.example.pam.data

import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer {
    val adminRepository: AdminRepository
}

class AdminContainer : AppContainer{
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override val adminRepository: AdminRepository by lazy {
        AdminRepositoryImpl(firestore)
    }
}