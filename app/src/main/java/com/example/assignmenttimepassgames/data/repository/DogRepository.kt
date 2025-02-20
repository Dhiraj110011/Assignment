package com.example.assignmenttimepassgames.data.repository

import android.content.Context
import com.example.assignmenttimepassgames.data.model.DogImage
import com.example.assignmenttimepassgames.data.api.RetrofitInstance
import com.example.assignmenttimepassgames.data.cache.DogImageCache

class DogRepository(private val context: Context) {
    private val api = RetrofitInstance.api
    private val cache = DogImageCache(context)

    suspend fun getRandomDog(): Result<DogImage> {
        return try {
            val response = api.getRandomDog()
            println("API Response: ${response.body()}")

            if (response.isSuccessful && response.body()?.status == "success") {
                val imageUrl = response.body()?.imageUrl?.replace("\\/", "/") ?: ""
                println("Processed URL: $imageUrl")

                val dogImage = DogImage(imageUrl = imageUrl)
                cache.addImage(dogImage)
                Result.success(dogImage)
            } else {
                Result.failure(Exception("Failed to fetch dog image"))
            }
        } catch (e: Exception) {
            println("Error fetching dog: ${e.message}")
            Result.failure(e)
        }
    }

    fun getCachedDogs(): List<DogImage> = cache.getAllImages()

    fun clearCache() = cache.clearCache()
}