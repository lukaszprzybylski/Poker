package com.example.myapplication.poker.model

data class SinglePlanet(
    val message: String,
    val result: Result
) {
    data class Result(
        val __v: Int,
        val _id: String,
        val description: String,
        val properties: Properties,
        val uid: String
    )
    data class Properties(
        val diameter: String,
        val rotation_period: String,
        val orbital_period: String,
        val gravity: String,
        val population: String,
        val climate: String,
        val terrain: String,
        val surface_water: String,
        val created: String,
        val edited: String,
        val name: String,
        val url: String
    )
}