package com.example.pam.Model

data class Admin(
    val id: String,
    val nama: String,
    val password: String
){
    constructor(): this("","","",)
}
data class makanan(
    val id: String,
    val namamkn: String,
    val harga: String,
    val jenis: String
){
    constructor(): this("","","","")
}

data class struck(
    val id: String,
    val nama: String,
    val password: String,
    val namamkn: String,
    val harga: String,
    val jenis: String
){
    constructor(): this("","","","","","",)

}