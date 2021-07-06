package com.example.movieapp.utils

import android.content.Context
import android.widget.Toast


fun Context.showToast(message: String, context: Context) {

    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

}

