package com.example.assignmenttimepassgames.data.cache

import android.content.Context
import android.util.LruCache
import com.example.assignmenttimepassgames.data.model.DogImage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class DogImageCache(context: Context) {
    private val CACHE_SIZE = 20
    private val PREFS_NAME = "dog_cache_prefs"
    private val cache = LruCache<String, DogImage>(CACHE_SIZE)
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    init {
        loadCacheFromPrefs()
    }

    fun addImage(dogImage: DogImage) {
        cache.put(dogImage.id, dogImage)
        saveCacheToPrefs()
    }

    fun getAllImages(): List<DogImage> = cache.snapshot().values.toList()

    fun clearCache() {
        cache.evictAll()
        prefs.edit().clear().apply()
    }

    private fun saveCacheToPrefs() {
        val editor = prefs.edit()
        val cacheJson = Gson().toJson(getAllImages())
        editor.putString("cached_dogs", cacheJson)
        editor.apply()
    }

    private fun loadCacheFromPrefs() {
        val cacheJson = prefs.getString("cached_dogs", null)
        if (cacheJson != null) {
            val type = object : TypeToken<List<DogImage>>() {}.type
            val dogImages = Gson().fromJson<List<DogImage>>(cacheJson, type)
            dogImages.forEach { addImage(it) }
        }
    }
}