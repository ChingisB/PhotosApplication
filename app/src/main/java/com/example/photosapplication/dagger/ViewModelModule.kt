package com.example.photosapplication.dagger

import com.example.photosapplication.models.PhotoResponse
import com.example.photosapplication.repository.PhotoRepository
import com.example.photosapplication.ui.newPhotos.NewPhotosViewModel
import com.example.photosapplication.ui.popularPhotos.PopularPhotosViewModel
import dagger.Module
import dagger.Provides


@Module
class ViewModelModule {
    @Provides
    fun provideNewPhotoViewModelFactory(photoRepository: PhotoRepository<PhotoResponse>): NewPhotosViewModel.Factory{
        return NewPhotosViewModel.Factory(photoRepository)
    }

    @Provides
    fun providePopularPhotoViewModelFactory(photoRepository: PhotoRepository<PhotoResponse>): PopularPhotosViewModel.Factory{
        return PopularPhotosViewModel.Factory(photoRepository)
    }
}