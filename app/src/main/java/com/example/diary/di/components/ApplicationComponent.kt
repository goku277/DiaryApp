package com.example.diary.di.components

import com.example.diary.App
import com.example.diary.di.modules.ApplicationModule
import com.example.diary.di.scope.ApplicationScope
import com.example.diary.ui.components.diary.DataEntryActivity
import com.example.diary.ui.components.diary.DiaryActivity
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class])
interface ApplicationComponent {
    fun inject(app: App)
    fun inject(diaryActivity: DiaryActivity)
    fun inject(dataEntryActivity: DataEntryActivity)
}