package com.example.photosapplication.dagger

import com.example.photosapplication.MainActivity
import com.example.photosapplication.ui.newPhotos.NewPhotosFragment
import com.example.photosapplication.ui.popularPhotos.PopularPhotosFragment
import dagger.Component

@Component(modules = [RetrofitModule::class, DataModule::class, ViewModelModule::class])
interface AppComponent {
    fun injectActivity(activity: MainActivity)

    fun injectNewPhotosFragment(fragment: NewPhotosFragment)

    fun injectPopularPhotosFragment(fragment: PopularPhotosFragment)

    @Component.Builder
    interface Builder{

        fun retrofitModule(retrofitModule: RetrofitModule): Builder

        fun build(): AppComponent
    }
}