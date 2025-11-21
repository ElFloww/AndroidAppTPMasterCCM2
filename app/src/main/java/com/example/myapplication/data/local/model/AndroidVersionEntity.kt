package com.example.myapplication.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "android_version")
data class AndroidVersionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "number")
    val number: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "year")
    val year: String,
)
