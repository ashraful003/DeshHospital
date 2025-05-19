package com.myapp.deshhospital.di

import com.myapp.deshhospital.util.ISharedPreferencesUtil
import com.myapp.deshhospital.util.SharePreferenceUtil
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface OtherInterfacesModule {
    @Binds
    fun bindSharePreferencesUtil(sharePreferencesUtil: SharePreferenceUtil): ISharedPreferencesUtil
}