package com.maxot.contactbook.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<T: BaseViewModel, V : ViewDataBinding> : AppCompatActivity() {

    lateinit var mViewDataBinding: V
    lateinit var mViewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }


    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): T

    abstract fun getBindingVariable(): Int


    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewModel = getViewModel()
        mViewDataBinding.setVariable(getBindingVariable(), getViewModel())
        mViewDataBinding.executePendingBindings()
    }

}