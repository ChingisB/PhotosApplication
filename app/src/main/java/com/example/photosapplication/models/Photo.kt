package com.example.photosapplication.models


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Photo(
    @SerializedName("dateCreate")
    val dateCreate: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: Image,
    @SerializedName("name")
    val name: String,
    @SerializedName("new")
    val new: Boolean,
    @SerializedName("popular")
    val popular: Boolean,
    @SerializedName("user")
    val user: String
): Serializable