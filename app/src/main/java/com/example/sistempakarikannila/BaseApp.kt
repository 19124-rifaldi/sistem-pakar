package com.example.sistempakarikannila

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp(): Application() {
    private var isInternetStable = false
    override fun onCreate() {
        super.onCreate()

        // Memantau perubahan status koneksi internet
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                isInternetStable = true
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                isInternetStable = false
            }
        })
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

    private val activityLifecycleCallbacks = object : ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            // Kosongkan atau tambahkan kode yang sesuai jika diperlukan
        }

        override fun onActivityStarted(activity: Activity) {
            // Kosongkan atau tambahkan kode yang sesuai jika diperlukan
        }

        override fun onActivityResumed(activity: Activity) {
            // Panggil fungsi showConnectionStatusDialog() saat Activity resumed
            showConnectionStatusSnackBar(activity)

        }

        override fun onActivityPaused(activity: Activity) {
            // Kosongkan atau tambahkan kode yang sesuai jika diperlukan
        }

        override fun onActivityStopped(activity: Activity) {
            // Kosongkan atau tambahkan kode yang sesuai jika diperlukan
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            // Kosongkan atau tambahkan kode yang sesuai jika diperlukan
        }

        override fun onActivityDestroyed(activity: Activity) {
            // Kosongkan atau tambahkan kode yang sesuai jika diperlukan
        }

    }
    private fun showConnectionStatusToast(context: Context) {
        if (!isInternetStable) {
            // Jika koneksi internet tidak stabil, baru tampilkan Toast
            Toast.makeText(context, "Tidak Ada Koneksi Internet", Toast.LENGTH_SHORT).show()
        }
    }
    private fun showConnectionStatusSnackBar( activity: Activity) {
        if(!isInternetStable){
            Snackbar.make(activity.findViewById(android.R.id.content), "Tidak Ada Koneksi Internet", Snackbar.LENGTH_LONG).show()
        }

    }

}