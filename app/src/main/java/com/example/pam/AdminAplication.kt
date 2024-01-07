package com.example.pam

import android.app.Application
import com.example.pam.data.AdminContainer

class AdminAplication : Application() {

    lateinit var container: AdminContainer

    override fun onCreate() {
        super.onCreate()

        container = AdminContainer()
    }
}