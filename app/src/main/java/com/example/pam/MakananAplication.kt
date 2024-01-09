package com.example.pam

import android.app.Application
import com.example.pam.data.MakananContainer

class MakananAplication : Application() {

    lateinit var container: MakananContainer

    override fun onCreate() {
        super.onCreate()

        container = MakananContainer()
    }
}