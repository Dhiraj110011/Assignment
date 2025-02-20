package com.example.assignmenttimepassgames.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmenttimepassgames.data.model.DogImage
import com.example.assignmenttimepassgames.data.repository.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class DogViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DogRepository(application)

    private val _currentDog = MutableStateFlow<DogImage?>(null)
    val currentDog: StateFlow<DogImage?> = _currentDog.asStateFlow()

    private val _cachedDogs = MutableStateFlow<List<DogImage>>(emptyList())
    val cachedDogs: StateFlow<List<DogImage>> = _cachedDogs.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadCachedDogs()
    }

    fun generateNewDog() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            repository.getRandomDog()
                .onSuccess { dog ->
                    _currentDog.value = dog
                    loadCachedDogs()
                }
                .onFailure { exception ->
                    _error.value = exception.message
                }

            _isLoading.value = false
        }
    }

    private fun loadCachedDogs() {
        _cachedDogs.value = repository.getCachedDogs()
    }

    fun clearCache() {
        repository.clearCache()
        _cachedDogs.value = emptyList()
        _currentDog.value = null
    }
}