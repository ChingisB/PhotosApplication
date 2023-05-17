package com.example.photosapplication.repository

import com.example.photosapplication.api.PhotoService
import com.example.photosapplication.models.PhotoResponse
import com.example.photosapplication.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Response
import javax.inject.Inject


class RetrofitPhotoRepository @Inject constructor(private val photoService: PhotoService) :
    PhotoRepository<PhotoResponse> {

    override val photos: MutableStateFlow<Resource<PhotoResponse>> =
        MutableStateFlow(Resource.Loading())

    override suspend fun getNewPhotos() {
        photos.update { Resource.Loading() }
        try {
            val response = photoService.getNewPhotos()
            photos.update { handleResponse(response) }
        } catch (e: Exception) {
            photos.update { Resource.Error(message = e.message.toString()) }
        }
    }

    override suspend fun getPopularPhotos() {
        photos.update { Resource.Loading() }
        try {
            val response = photoService.getPopularPhotos()
            photos.update { handleResponse(response) }
        } catch (e: Exception) {
            photos.update { Resource.Error(message = e.message.toString()) }
        }

    }

    private fun handleResponse(response: Response<PhotoResponse>): Resource<PhotoResponse> {
        if (response.isSuccessful) {
            response.body()?.let { photoResponse ->
                return Resource.Success(photoResponse)
            }
        }
        return Resource.Error(message = response.message())

    }
}