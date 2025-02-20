package com.example.assignmenttimepassgames.data.api.model

import com.google.gson.annotations.SerializedName

data class DogApiResponse(
    @SerializedName("message") val imageUrl: String,
    @SerializedName("status") val status: String
)

