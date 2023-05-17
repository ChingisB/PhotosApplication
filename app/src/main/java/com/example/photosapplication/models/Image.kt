package com.example.photosapplication.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Image(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
): Serializable