package com.example.githubsearchapp.common.utils

import android.util.Log

fun handleNetworkError(e: Exception) {
    Log.d("Network request exception", "Exception: $e")
}