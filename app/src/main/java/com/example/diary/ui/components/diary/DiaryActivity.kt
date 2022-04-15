package com.example.diary.ui.components.diary

//import kotlinx.android.synthetic.main.custom_toolbar.*
import android.annotation.SuppressLint
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.diary.App
import com.example.diary.R
import com.example.diary.local.AppDatabase
import com.example.diary.local.LocalRepository
import com.example.diary.models.DataEntry
import com.example.diary.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import kotlin.math.log


/**
 * Created by Shanmuka on 16-1-2019.
 */
class DiaryActivity : BaseActivity<DiaryContract.View, DiaryContract.Presenter>(), DiaryContract.View {

    @Inject
    lateinit var diaryPresenter: DiaryPresenter

    private var adapter: DataEntryAdapter? = null
    private lateinit var repository: LocalRepository
    private var dataList = mutableListOf<DataEntry>()
    private var tag= "DiaryActivity.kt"
    override fun initUI() {
        floatingActionButton.setOnClickListener {
            presenter?.onAddButtonClicked("DiaryActivity")
        }
        repository = (application as App).repository
        var list= AppDatabase.getDatabase(applicationContext).dataEntryDao().getAll()
        Log.d(tag, "DiaryActivity list.size : ${list.size}")

        if (list.size > 0) {
            loadData()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadData() {
        Log.d(tag, "Inside loadData()...")

        recyclerView.layoutManager = GridLayoutManager(applicationContext,2)
        adapter = DataEntryAdapter(applicationContext)
        recyclerView.adapter = adapter

        dataList = AppDatabase.getDatabase(applicationContext).dataEntryDao().getAll()
        Log.d(tag, "Inside loadData : dataList : $dataList")
        adapter?.setDataList(dataList)
    }


    override fun onResume() {
        super.onResume()

    }

    override fun initPresenter() = diaryPresenter

    override fun injectDependencies() = getApplicationComponent().inject(this)

    override fun getLayoutResId() = R.layout.activity_main

    override fun finishView() = finish()

}