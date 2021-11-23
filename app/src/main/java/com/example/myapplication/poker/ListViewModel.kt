package com.example.myapplication.poker

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.poker.model.PlanetsModel
import com.example.myapplication.poker.model.SinglePlanet
import kotlinx.coroutines.*

class ListViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    val allPlanets = MutableLiveData<PlanetsModel>()
    val singlePlanet = MutableLiveData<SinglePlanet>()
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    private val loading = MutableLiveData<Boolean>()

    fun getAllPlanets() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getAllPlanets()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    allPlanets.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    fun getPlanet(uid: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getPlanet(uid)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    singlePlanet.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
       Log.d("Poker exception", message)
    }
}