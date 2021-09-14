package com.example.repository.networkStatus

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest

class AndroidNetworkStatus(context: Context) : INetworkStatus {

    private var isOnline = false

    init {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isOnline = true
            }

            override fun onUnavailable() {
                isOnline = false
            }

            override fun onLost(network: Network) {
                isOnline = false
            }
        })
    }

    override fun isOnline() = isOnline
}
