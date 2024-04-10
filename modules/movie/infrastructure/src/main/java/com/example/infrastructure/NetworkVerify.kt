package com.example.infrastructure

class NetworkVerify {

    fun isNetworkConnection(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec("/system/bin/ping -c 1 google.com")
            val exitCode = process.waitFor()
            exitCode == 0
        } catch (e: Exception) {
            false
        }
    }

}