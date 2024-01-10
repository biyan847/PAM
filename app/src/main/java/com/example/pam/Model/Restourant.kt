package com.example.pam.Model


data class Makanan(
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
data class Pelanggan(
    val id: String,
    val pelanggan: String,
    val nomormeja : String,
)
{
    constructor(): this("","","",)
}