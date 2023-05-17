package com.example.photosapplication.api

object Config {
    private const val baseUrl = "https://gallery.prod1.webant.ru/api/"
    private const val mediaUrl = "https://gallery.prod1.webant.ru/media/"

    fun getBaseUrl(): String{
        return baseUrl
    }

    fun getMediaUrl(): String{
        return mediaUrl
    }
}