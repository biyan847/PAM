package com.example.pam.Model

data class Admin(
    val id: String,
    val nama: String,
    val password: String
){
    constructor(): this("","","",)
}
