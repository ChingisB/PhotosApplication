package com.example.photosapplication.ui.newPhotos

import androidx.lifecycle.*
import com.example.photosapplication.models.PhotoResponse
import com.example.photosapplication.repository.PhotoRepository
import com.example.photosapplication.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewPhotosViewModel(private val photoRepository: PhotoRepository<PhotoResponse>) :
    ViewModel() {

    private val _photos: MutableLiveData<Resource<PhotoResponse>> = MutableLiveData()
    val photos: LiveData<Resource<PhotoResponse>> = _photos
    private val page = 1


    init {
        getPhotos()
    }

    fun getPhotos() =
        viewModelScope.launch {
            photoRepository.getNewPhotos()
            photoRepository.photos.collect() { response ->
                _photos.postValue(response)
            }
        }

    class Factory(val photoRepository: PhotoRepository<PhotoResponse>) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewPhotosViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewPhotosViewModel(photoRepository) as T
            }
            throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
        }
    }
}

