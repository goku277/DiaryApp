package com.example.diary.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    var title: String = "",
    var data: String = ""
)