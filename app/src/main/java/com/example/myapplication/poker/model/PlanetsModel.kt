package com.example.myapplication.poker.model

import com.google.gson.annotations.SerializedName

data class PlanetsModel(@SerializedName("results") val items: List<Result>) {

    data class Result(
        val name: String,
        val uid: String,
        val url: String
    )
}

