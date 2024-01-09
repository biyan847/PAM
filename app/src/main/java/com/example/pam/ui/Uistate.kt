package com.example.pam.ui


import com.example.pam.Model.Makanan


data class AddUIState(
    val addEvent: AddEvent = AddEvent(),
)

data class AddEvent(
    val id: String = "",
    val nama: String = "",
    val Harga: String = "",
    val Jenis: String = "",
)

fun AddEvent.toAdmin(): Makanan = Makanan(
    id = id,
    namamkn = nama,
    harga = Harga,
    jenis = Jenis

)

data class DetailUIState(
    val addEvent: AddEvent = AddEvent(),
)

fun Makanan.toDetailAdmin(): AddEvent =
    AddEvent(
        id = id,
        nama = namamkn,
        Harga = harga,
        Jenis = jenis
    )

fun Makanan.toUIStateAdmin(): AddUIState = AddUIState(
    addEvent = this.toDetailAdmin()
)

data class HomeUIState(
    val listAdmin: List<Makanan> = listOf(),
    val dataLength: Int = 0
)