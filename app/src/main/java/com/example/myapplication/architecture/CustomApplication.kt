package com.example.myapplication.architecture

import android.app.Application
import androidx.room.Room
import com.example.myapplication.data.local.CustomRoomDatabase
import com.example.myapplication.data.services.firebase.RemoteConfigManager

class CustomApplication : Application() {


    companion object {
        lateinit var instance: CustomApplication
    }


    val applicationDatabase: CustomRoomDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            CustomRoomDatabase::class.java,
            "MyDatabaseName"
        ).fallbackToDestructiveMigration(false)
            .build()
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        RemoteConfigManager.init()
    }
}
