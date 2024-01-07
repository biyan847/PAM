package com.example.pam.ui

import com.example.pam.Model.Admin

data class AddUIState(
    val addEvent: AddEvent = AddEvent(),
)

data class AddEvent(
    val id: String = "",
    val nama: String = "",
    val password: String = "",
)

fun AddEvent.toAdmin() = Admin(
    id = id,
    nama = nama,
    password = password
)

data class DetailUIState(
    val addEvent: AddEvent = AddEvent(),
)

fun Admin.toDetailAdmin(): AddEvent =
    AddEvent(
        id = id,
        nama = nama,
        password = password
    )

fun Admin.toUIStateAdmin(): AddUIState = AddUIState(
    addEvent = this.toDetailAdmin()
)

data class HomeUIState(
    val listAdmin: List<Admin> = listOf(),
    val dataLength: Int = 0
)