package com.example.diary.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.diary.App

abstract class  BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> : AppCompatActivity(),
    BaseContract.View {

    protected var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        oneSignal()
        setContentView(getLayoutResId())

        injectDependencies()

        val viewModel: BaseViewModel<V, P> = ViewModelProviders.of(this).get(BaseViewModel<V, P>()::class.java)

        viewModel.presenter = viewModel.presenter ?: initPresenter()

        presenter = viewModel.presenter
        presenter?.attachView(this as V)
        presenter?.onCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }


    override fun onStart() {
        super.onStart()
        presenter?.onStarted()
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResumed()
    }

    fun getApplicationComponent() = (application as App).applicationComponent

    protected abstract fun initPresenter(): P

    protected abstract fun injectDependencies()

    protected abstract fun getLayoutResId(): Int
}