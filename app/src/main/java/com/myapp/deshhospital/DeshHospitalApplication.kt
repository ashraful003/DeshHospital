package com.myapp.deshhospital

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DeshHospitalApplication:Application() {
    companion object{
        @JvmStatic
        fun getApplication(context: Context)=context.applicationContext as DeshHospitalApplication
    }
}