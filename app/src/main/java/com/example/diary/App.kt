package com.example.diary
import android.app.Application
import com.example.diary.di.components.ApplicationComponent
import com.example.diary.di.components.DaggerApplicationComponent
import com.example.diary.di.modules.ApplicationModule
import com.example.diary.local.AppDatabase
import com.example.diary.local.LocalRepository
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        applicationComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingServiceInjector
    }

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { LocalRepository(database.dataEntryDao()) }

    val applicationComponent: ApplicationComponent by lazy<ApplicationComponent>(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}