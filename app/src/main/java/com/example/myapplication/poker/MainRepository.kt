package com.example.myapplication.poker

import com.example.myapplication.poker.network.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllPlanets() = retrofitService.getAllPlanets()
    suspend fun getPlanet(uid: String) = retrofitService.getPlanet(uid)
}