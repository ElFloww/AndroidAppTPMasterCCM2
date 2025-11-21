package com.example.myapplication.data.repository

import MyAndroidModelData
import com.example.myapplication.architecture.CustomApplication
import com.example.myapplication.data.local.model.AndroidVersionEntity
import com.example.myapplication.data.mapping.toDataObject
import com.example.myapplication.data.mapping.toRoomObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AndroidVersionRepository {
    private val androidVersionDao = CustomApplication.instance.applicationDatabase.androidVersionDao()

    fun selectAllAndroidVersion(): Flow<List<MyAndroidModelData>> {
        return androidVersionDao.selectAll().map { androidVersionEntity: List<AndroidVersionEntity> ->
            androidVersionEntity.toDataObject()
        }
    }

    fun insertAndroidVersion(myAndroidModelData: MyAndroidModelData) {
        androidVersionDao.insert(myAndroidModelData.toRoomObject())
    }

    fun deleteAllAndroidVersion() {
        androidVersionDao.deleteAll()
    }
}
