package com.example.myapplication.data.mapping

import MyAndroidModelData
import com.example.myapplication.data.local.model.AndroidVersionEntity

fun MyAndroidModelData.toRoomObject(): AndroidVersionEntity {
    return AndroidVersionEntity(
        id = id,
        name = versionName,
        number = versionNumber,
        url = versionUrl,
        year = versionYear,
    )
}

fun List<AndroidVersionEntity>.toDataObject(): List<MyAndroidModelData> {
    return this.map { eachItem ->
        MyAndroidModelData(
            id = eachItem.id,
            versionNumber = eachItem.number,
            versionName = eachItem.name,
            versionUrl = eachItem.url,
            versionYear = eachItem.year,
        )
    }
}
