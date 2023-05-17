package com.example.photosapplication.dagger

import android.content.Context
import com.example.photosapplication.api.Config
import com.example.photosapplication.api.PhotoService
import com.example.photosapplication.api.interceptors.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule(private val context: Context) {

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Config.getBaseUrl()).client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideHttpClient(networkConnectionInterceptor: NetworkConnectionInterceptor): OkHttpClient{
        return OkHttpClient().newBuilder().addNetworkInterceptor(networkConnectionInterceptor).build()
    }

    @Provides
    fun providePhotoService(retrofit: Retrofit): PhotoService {
        return retrofit.create(PhotoService::class.java)
    }

    @Provides
    fun provideNetworkConnectionInterceptor(): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(context)
    }
}