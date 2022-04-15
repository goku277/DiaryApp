package com.example.diary.local

import androidx.annotation.WorkerThread
import com.example.diary.dao.DataEntryDao
import com.example.diary.models.DataEntry

@WorkerThread
class LocalRepository(private val dataEntryDao: DataEntryDao) {

    fun insertDataEntry(dataEntry: DataEntry) = dataEntryDao.insert(dataEntry)

    fun getAllDataEntries() = dataEntryDao.getAll()

    fun deleteAllDataEntries() = dataEntryDao.deleteAll()
}