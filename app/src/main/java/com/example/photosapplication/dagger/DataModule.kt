package com.example.photosapplication.dagger

import com.example.photosapplication.models.PhotoResponse
import com.example.photosapplication.repository.PhotoRepository
import com.example.photosapplication.repository.RetrofitPhotoRepository
import dagger.Binds
import dagger.Module


@Module(includes = [DataModuleBinds::class])
class DataModule


@Module
interface DataModuleBinds {
    @Binds
    fun bindPhotoRepository(retrofitPhotoRepository: RetrofitPhotoRepository): PhotoRepository<PhotoResponse>
}