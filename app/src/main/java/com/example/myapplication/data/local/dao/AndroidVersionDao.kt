package com.example.myapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.local.model.AndroidVersionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AndroidVersionDao {
    @Query("SELECT * FROM android_version ORDER BY name ASC")
    fun selectAll(): Flow<List<AndroidVersionEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(androidVersion: AndroidVersionEntity)


    @Query("DELETE FROM android_version")
    fun deleteAll()
}
