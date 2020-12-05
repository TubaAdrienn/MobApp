package com.example.loveapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoveDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(love: LoveResultSave)

    @Query("SELECT * from love_table WHERE id = :key")
    fun getResultWithId(key: Long): LiveData<LoveResultSave>

    @Query("SELECT * FROM love_table ORDER BY id DESC")
    fun getAllResults(): LiveData<List<LoveResultSave>>

}
