package com.example.diary.ui.components.diary

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.diary.local.AppDatabase
import com.example.diary.local.LocalRepository
import com.example.diary.models.DataEntry
import com.example.diary.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Shanmuka on 16-1-2019.
 */
class DiaryPresenter @Inject constructor(
    private val context: Context,
) :
    BasePresenter<DiaryContract.View>(), DiaryContract.Presenter {
    private var tag= "DiaryPresenter.kt"
    override fun onAddButtonClicked(activityName: String) {
        Log.d(tag, "onAddButtonClicked: context : $activityName")
        if (activityName.contains("DiaryActivity")) {
            val intent = Intent(context, DataEntryActivity::class.java)
            context.startActivity(intent)
        }
        if (activityName.contains("DataEntry")) {
            val intent = Intent(context, DiaryActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun makeNote(applicationContext: Context, repository: LocalRepository, title: String, note: String) {
        if (!AppDatabase.getDatabase(applicationContext).isOpen) {
            AppDatabase.getDatabase(applicationContext).dataEntryDao().insert(
                DataEntry(
                    title = title,
                    data = note
                )
            )
        }
        else {
            repository.insertDataEntry(
                DataEntry(
                    title = title,
                    data = note
                )
            )
        }

        val notes= AppDatabase.getDatabase(applicationContext).dataEntryDao()
            .getAll()
        if (notes.size > 0) {
            Log.d(tag, "Inside diary presenter makeNote notes : $notes")
            applicationContext.startActivity(Intent(applicationContext, DiaryActivity::class.java))
        }
    }

    override fun onCreated() {
        super.onCreated()
        getView()?.initUI()
        getView()?.hideMenuItems()
        getView()?.replaceFragment()
    }
}