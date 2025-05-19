package com.myapp.deshhospital.util

class DHActivityUtil(private val activityListener:ActivityListener) {
    fun hideBottomNavigation(hide: Boolean){
        activityListener.hideBottomNavigation(hide)
    }
    fun setFullScreenLoading(short: Boolean){
        activityListener.setFullScreenLoading(short)
    }

    interface ActivityListener{
        fun hideBottomNavigation(hide:Boolean)
        fun setFullScreenLoading(short: Boolean)
    }
}