package com.myapp.deshhospital.di

import android.app.Activity
import com.myapp.deshhospital.util.DHActivityUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object DeshHospitalActivityModule {
    @Provides
    fun providedDHActivityUtil(activity: Activity): DHActivityUtil {
        return DHActivityUtil(activity as DHActivityUtil.ActivityListener)
    }
}