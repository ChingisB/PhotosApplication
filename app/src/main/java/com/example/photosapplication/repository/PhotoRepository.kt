package com.example.photosapplication.repository

import com.example.photosapplication.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow

interface PhotoRepository<T> {

    val photos: MutableStateFlow<Resource<T>>

    suspend fun getNewPhotos()

    suspend fun getPopularPhotos()
}