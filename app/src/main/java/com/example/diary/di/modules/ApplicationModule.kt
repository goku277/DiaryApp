package com.example.diary.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.DisplayMetrics
import com.example.diary.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * Created by Shanmuka on 5/10/2019.
 */
@Module
class ApplicationModule(private val application: Application) {

    @ApplicationScope
    @Provides
    fun provideApplicationContext(): Context = application.applicationContext

    @ApplicationScope
    @Provides
    fun providerDisplayMetrics(): DisplayMetrics = application.resources.displayMetrics

}