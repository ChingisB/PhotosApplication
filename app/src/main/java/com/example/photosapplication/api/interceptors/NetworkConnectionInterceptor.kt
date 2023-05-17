package com.example.photosapplication.api.interceptors

import android.content.Context
import android.net.ConnectivityManager
import com.example.photosapplication.exceptions.NetworkConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val context: Context) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            throw NetworkConnectionException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isConnected(): Boolean {
        val connectionService =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectionService.activeNetwork != null && connectionService.isDefaultNetworkActive
    }


}