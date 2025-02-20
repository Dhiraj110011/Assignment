package com.example.assignmenttimepassgames.data.api

import com.example.assignmenttimepassgames.data.api.model.DogApiResponse
import retrofit2.Response
import retrofit2.http.GET


interface DogApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDog(): Response<DogApiResponse>
}