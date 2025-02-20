package com.example.assignmenttimepassgames.data.model

import java.util.UUID

data class DogImage(
    val id: String = UUID.randomUUID().toString(),
    val imageUrl: String,
)
