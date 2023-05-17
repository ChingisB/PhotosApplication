package com.example.photosapplication.exceptions

class NetworkConnectionException: Exception() {

    override val message: String = "No internet connection"
}