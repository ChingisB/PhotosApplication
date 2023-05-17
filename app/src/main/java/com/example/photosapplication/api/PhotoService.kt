package com.example.photosapplication.api

import com.example.photosapplication.models.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {

    @GET("photos/")
    suspend fun getNewPhotos(
        @Query("new") new: Boolean = true,
        @Query("popular") popular: Boolean? = false
    ): Response<PhotoResponse>

    @GET("photos/")
    suspend fun getPopularPhotos(@Query("new") new: Boolean = false,
                                 @Query("popular") popular: Boolean? = true): Response<PhotoResponse>
}