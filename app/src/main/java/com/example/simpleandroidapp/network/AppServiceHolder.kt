package com.example.simpleandroidapp.network

class AppServiceHolder {
    private var appService: AppService? = null

    fun appService(): AppService? {
        return appService
    }
}