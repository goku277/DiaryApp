package com.example.diary.ui.components.diary

import android.content.Context
import com.example.diary.local.LocalRepository
import com.example.diary.ui.base.BaseContract

/**
 * Created by Shanmuka on 16-1-2019.
 */
interface DiaryContract {

    interface View : BaseContract.View {

        fun initUI()

        fun finishView() {}

        fun hideMenuItems() {}

        fun replaceFragment() {}
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onAddButtonClicked(activityName: String)
        fun makeNote(context: Context, repository: LocalRepository, title: String, note: String)
    }

}