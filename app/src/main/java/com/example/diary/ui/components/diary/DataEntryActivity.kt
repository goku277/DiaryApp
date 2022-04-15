package com.example.diary.ui.components.diary

import android.util.Log
import android.widget.Toast
import com.example.diary.App
import com.example.diary.R
import com.example.diary.local.AppDatabase
import com.example.diary.local.LocalRepository
import com.example.diary.models.DataEntry
import com.example.diary.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_data_entry.*
import javax.inject.Inject

class DataEntryActivity : BaseActivity<DiaryContract.View, DiaryContract.Presenter>(), DiaryContract.View {
    @Inject
    lateinit var diaryPresenter: DiaryPresenter

    private lateinit var repository: LocalRepository

    private var adapter: DataEntryAdapter? = null

    private var tag= "DataEntry.kt"

    override fun initPresenter() = diaryPresenter

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId() = R.layout.activity_data_entry

    override fun initUI() {
        Toast.makeText(this, "Inside DataEntry class...", Toast.LENGTH_SHORT).show()
        Log.d(tag, "Inside DataEntry class...")
        titleInput.setBackgroundResource(android.R.color.transparent)
        noteInput.setBackgroundResource(android.R.color.transparent)
        repository = (application as App).repository
        floatingActionButton.setOnClickListener {
            if (titleInput.text.isNotEmpty() && noteInput.text.isNotEmpty()) {
                presenter?.makeNote(applicationContext, repository, titleInput.text.toString(), noteInput.text.toString())
                finish()
            }
        }
    }

}