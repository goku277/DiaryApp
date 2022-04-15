package com.example.diary.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.diary.models.DataEntry

@Dao
interface DataEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dataEntry: DataEntry)

    @Query("SELECT * FROM DataEntry")
    fun getAll(): MutableList<DataEntry>

    @Query("DELETE FROM DataEntry")
    fun deleteAll()
}