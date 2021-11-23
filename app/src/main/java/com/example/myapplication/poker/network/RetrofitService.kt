package com.example.myapplication.poker.network

import com.example.myapplication.poker.network.Constant.BASE_URL
import com.example.myapplication.poker.model.PlanetsModel
import com.example.myapplication.poker.model.SinglePlanet
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("planets")
    suspend fun getAllPlanets() : Response<PlanetsModel>

    @GET("planets/{id}")
    suspend fun getPlanet( @Path("id") id: String) : Response<SinglePlanet>

    companion object {
        private var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}