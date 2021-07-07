package com.example.movieapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast


fun showToast(message: String, context: Context) {


    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

}

fun checkConnection(context: Context): Boolean {
    val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connMgr.activeNetworkInfo
    // connected to the internet
    // connected to the mobile provider's data plan
    if (activeNetworkInfo != null) return if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {

        true
    } else
    {
        activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
    }
    return false
}

