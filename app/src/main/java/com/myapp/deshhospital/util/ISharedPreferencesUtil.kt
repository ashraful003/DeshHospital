package com.myapp.deshhospital.util

interface ISharedPreferencesUtil {
    fun logout()
    fun getAuthToken():String?
    fun setAuthToken(token:String)
}